package edu.fdzc.im.client.handler;

import edu.fdzc.im.protocol.login.LogoutResponsePacket;
import edu.fdzc.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 退出登录响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {

        SessionUtil.unBindSession(channelHandlerContext.channel());
    }
}
