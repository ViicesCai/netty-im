package edu.fdzc.im.client.handler;

import edu.fdzc.im.protocol.chat.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 退出群聊响应控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.getSuccess()) {
            System.out.println("退出群聊 [ " + quitGroupResponsePacket.getGroupId() + " ] 成功!");

        } else {
            System.out.println("退出群聊 [ " + quitGroupResponsePacket.getGroupId() + " ] 失败!");
        }
    }
}
