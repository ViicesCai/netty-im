package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.protocol.login.LogoutRequestPacket;
import edu.fdzc.im.common.protocol.login.LogoutResponsePacket;
import edu.fdzc.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 退出登录请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    /**
     * 实例对象
     */
    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());

        System.out.println(logoutRequestPacket.getName() + " 已退出.");
        LogoutResponsePacket responsePacket = new LogoutResponsePacket();
        responsePacket.setSuccess(true);

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
