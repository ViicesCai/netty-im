package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.util.LoginUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 认证控制器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    /**
     * 实例对象
     */
    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() { }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();

        } else {
            // 已登录：移除认证
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("登录验证完毕，移除 AuthHandler");

        } else {
            System.out.println("无登录验证，强制关闭连接");
        }
    }
}
