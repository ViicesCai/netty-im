package edu.fdzc.im.client.handler;

import edu.fdzc.im.common.protocol.chat.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * 心跳定时器控制器
 *
 * @author Viices Cai
 * @time 2022/3/23
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 心跳间隔
     */
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    /**
     * 心跳包发送策略
     *
     * @param ctx 上下文
     */
    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {

            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
