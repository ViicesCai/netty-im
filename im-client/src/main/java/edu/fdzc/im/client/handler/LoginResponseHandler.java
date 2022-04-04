package edu.fdzc.im.client.handler;

import edu.fdzc.im.common.data.Session;
import edu.fdzc.im.common.protocol.login.LoginResponsePacket;
import edu.fdzc.im.common.util.LoginUtil;
import edu.fdzc.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 登录响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if (loginResponsePacket.getSuccess()) {
            System.out.println(new Date() + ": [ " + userName + " ] 登录成功, userId : " + userId);

            LoginUtil.markAsLogin(channelHandlerContext.channel());
            SessionUtil.bindSession(new Session(userId, userName), channelHandlerContext.channel());

        } else {
            System.out.println(new Date() + ": [ " + userName + " ] 登录失败，原因 : " + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接关闭!");
    }
}
