package edu.fdzc.im.server;

import edu.fdzc.im.common.coder.PacketCoderHandler;
import edu.fdzc.im.common.coder.Spliter;
import edu.fdzc.im.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * IM 服务端
 *
 * @author Viices Cai
 * @time 2022/4/4
 */
public class IMServer {

    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {

                        // 空闲检测
                        channel.pipeline().addLast(new IMIdleStateHandler());
                        // 拆包器
                        channel.pipeline().addLast(new Spliter());
                        // 编码器
                        channel.pipeline().addLast(PacketCoderHandler.INSTANCE);
                        // 登录请求
                        channel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 心跳请求
                        channel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        // 登录验证
                        channel.pipeline().addLast(AuthHandler.INSTANCE);
                        // IM 请求
                        channel.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });

        bind(serverBootstrap, port);
    }

    /**
     * 端口绑定
     *
     * @param serverBootstrap 服务启动器
     * @param port 端口
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {

            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "] 绑定成功!");

            } else {
                System.err.println(new Date() + ": 端口[" + port + "] 绑定失败!");
            }
        });
    }
}
