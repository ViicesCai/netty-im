package edu.fdzc.im.client.handler;

import edu.fdzc.im.common.protocol.chat.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 加入群聊响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.getSuccess()) {
            System.out.println("加入群 [ " + joinGroupResponsePacket.getGroupId() + " ] 成功!");

        } else {
            System.err.println("加入群 [ " + joinGroupResponsePacket.getGroupId() + " ] 失败，原因: " + joinGroupResponsePacket.getReason());
        }
    }
}
