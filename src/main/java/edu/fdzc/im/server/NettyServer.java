package edu.fdzc.im.server;

import edu.fdzc.im.coder.PacketCoderHandler;
import edu.fdzc.im.coder.PacketDecoder;
import edu.fdzc.im.coder.PacketEncoder;
import edu.fdzc.im.coder.Spliter;
import edu.fdzc.im.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * Netty 服务端
 *
 * @author Viices Cai
 * @time 2022/3/7
 */
public class NettyServer {

    /**
     * 端口号
     */
    private static final int PORT = 8000;

    public static void main(String[] args) {
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

                        // 拆包器
                        channel.pipeline().addLast(new Spliter());
                        // 编码器
                        channel.pipeline().addLast(PacketCoderHandler.INSTANCE);
                        // 登录请求
                        channel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 登录验证
                        channel.pipeline().addLast(AuthHandler.INSTANCE);
                        // IM 请求
                        channel.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });

        bind(serverBootstrap, PORT);
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
