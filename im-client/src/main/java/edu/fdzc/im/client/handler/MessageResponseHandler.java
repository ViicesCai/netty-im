package edu.fdzc.im.client.handler;

import edu.fdzc.im.common.protocol.message.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 消息回复控制器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {

        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();

        System.out.println(new Date() + " : [ " + fromUserId + ":" + fromUserName + " ] : " + messageResponsePacket.getMessage());
    }
}
