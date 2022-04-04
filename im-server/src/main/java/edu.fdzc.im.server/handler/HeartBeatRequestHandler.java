package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.protocol.chat.HeartBeatRequestPacket;
import edu.fdzc.im.common.protocol.chat.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 心跳请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/23
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    /**
     * 实例对象
     */
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {

        channelHandlerContext.writeAndFlush(new HeartBeatResponsePacket());
    }
}
