package edu.fdzc.im.server.handler;

import edu.fdzc.im.data.Session;
import edu.fdzc.im.protocol.message.MessageRequestPacket;
import edu.fdzc.im.protocol.message.MessageResponsePacket;
import edu.fdzc.im.util.LoginUtil;
import edu.fdzc.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        // 获取发送者的会话
        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        // 构造消息响应包
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setMessage(messageRequestPacket.getMessage());

        // 获取接收者的 Channel
        Channel channel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        if (channel != null && LoginUtil.hasLogin(channel)) {
            System.out.println(session.getUserId() + " Send [ " + messageRequestPacket.getMessage() + " ]  To : " + messageRequestPacket.getToUserId());
            channel.writeAndFlush(responsePacket);

        } else {
            System.err.println("[ " + messageRequestPacket.getToUserId() + " ] 不在线，发送失败!");
        }
    }
}
