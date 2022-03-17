package edu.fdzc.im.server.handler;

import edu.fdzc.im.data.Session;
import edu.fdzc.im.protocol.login.LoginRequestPacket;
import edu.fdzc.im.protocol.login.LoginResponsePacket;
import edu.fdzc.im.util.IDUtil;
import edu.fdzc.im.util.LoginUtil;
import edu.fdzc.im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 登录请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    /**
     * 实例对象
     */
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        String userName = loginRequestPacket.getUserName();

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(userName);

        if (valid(loginRequestPacket)) {
            String userId = IDUtil.randomUserId();

            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(userId);

            System.out.println(new Date() + "[ " + userName + " ] : 登录成功!");

            LoginUtil.markAsLogin(channelHandlerContext.channel());
            SessionUtil.bindSession(new Session(userId, userName), channelHandlerContext.channel());

        } else {
            loginResponsePacket.setReason("账号密码校验失败!");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + " : 登录失败!");
        }

        // 登录响应
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 验证登录请求
     *
     * @param loginRequestPacket 登录请求包
     * @return 验证结果
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {

        return "admin".equals(loginRequestPacket.getPassword());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        SessionUtil.unBindSession(ctx.channel());
    }
}
