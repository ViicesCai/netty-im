package edu.fdzc.im.client.handler;

import edu.fdzc.im.common.protocol.message.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 群聊消息响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/10
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromGroupId = groupMessageResponsePacket.getFromGroupId();
        String fromUser = groupMessageResponsePacket.getFromUser().getUserName();

        System.out.println("收到群 [ " + fromGroupId + " ] 中 [ " + fromUser + " ] 发来的消息: " + groupMessageResponsePacket.getMessage());
    }
}
