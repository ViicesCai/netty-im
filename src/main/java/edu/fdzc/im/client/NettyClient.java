package edu.fdzc.im.client;

import edu.fdzc.im.client.console.ConsoleCommandManager;
import edu.fdzc.im.client.console.impl.LoginConsoleCommand;
import edu.fdzc.im.client.handler.*;
import edu.fdzc.im.coder.PacketDecoder;
import edu.fdzc.im.coder.PacketEncoder;
import edu.fdzc.im.coder.Spliter;
import edu.fdzc.im.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Netty 客户端
 *
 * @author Viices Cai
 * @time 2022/3/7
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {

                        // 拆包器
                        channel.pipeline().addLast(new Spliter());
                        // 译码器
                        channel.pipeline().addLast(new PacketDecoder());
                        // 登录响应
                        channel.pipeline().addLast(new LoginResponseHandler());
                        // 消息响应
                        channel.pipeline().addLast(new MessageResponseHandler());
                        // 创建群聊响应
                        channel.pipeline().addLast(new CreateGroupResponseHandler());
                        // 加入群聊响应
                        channel.pipeline().addLast(new JoinGroupResponseHandler());
                        // 获取群成员响应
                        channel.pipeline().addLast(new ListGroupMembersResponseHandler());
                        // 退出群聊响应
                        channel.pipeline().addLast(new QuitGroupResponseHandler());
                        // 群聊消息响应
                        channel.pipeline().addLast(new GroupMessageResponseHandler());
                        // 退出登录响应
                        channel.pipeline().addLast(new LogoutResponseHandler());
                        // 解码器
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int rerty) {
        bootstrap.connect(host, port).addListener(future -> {
           if (future.isSuccess()) {
               System.out.println(new Date() + ": 连接成功! 启动控制台线程...");

               // 启动控制台线程
               Channel channel = ((ChannelFuture) future).channel();
               startConsoleThread(channel);

           } else if (rerty == 0) {
               System.out.println("重试次数已用完，放弃连接!");

           } else {
               int order = (MAX_RETRY - rerty) + 1;
               int delay = 1 << order;
               System.out.println(new Date() + ": 连接失败，第" + order + "次重连...");
               bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, rerty - 1), delay, TimeUnit.SECONDS);
           }
        });
    }

    /**
     * 控制台线程：接收客户端的控制台输入
     *
     * @param channel 通信通道
     */
    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        Scanner in = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!LoginUtil.hasLogin(channel)) {
                   loginConsoleCommand.execute(in, channel);

                } else {
                    consoleCommandManager.execute(in, channel);
                }
            }
        }).start();
    }
}
