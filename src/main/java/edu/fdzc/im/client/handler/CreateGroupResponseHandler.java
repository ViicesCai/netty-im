package edu.fdzc.im.client.handler;

import edu.fdzc.im.protocol.chat.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 创建群聊响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {

        System.out.print("群创建成功，id : [ " + createGroupResponsePacket.getGroupId() + " ] ");
        System.out.println("群成员 : " + createGroupResponsePacket.getUserNames());
    }
}
