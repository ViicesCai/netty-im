package edu.fdzc.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * IM 闲置状态控制器
 *
 * @author Viices Cai
 * @time 2022/3/23
 */
public class IMIdleStateHandler extends IdleStateHandler {

    /**
     * 数据读操作的闲置时间
     */
    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读取到数据，关闭连接.");
        ctx.channel().close();
    }
}
