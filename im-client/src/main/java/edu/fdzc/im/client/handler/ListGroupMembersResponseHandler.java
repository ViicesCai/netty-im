package edu.fdzc.im.client.handler;

import edu.fdzc.im.common.protocol.chat.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 群聊用户列表响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        System.out.println("群 [ " + listGroupMembersResponsePacket.getGroupId() + " ] 中的人包括: " + listGroupMembersResponsePacket.getSessions());
    }
}
