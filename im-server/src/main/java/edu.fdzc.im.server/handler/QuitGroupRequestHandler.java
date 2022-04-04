package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.protocol.chat.QuitGroupRequestPacket;
import edu.fdzc.im.common.protocol.chat.QuitGroupResponsePacket;
import edu.fdzc.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 退出群聊请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        // 获取群聊对应的 ChannelGroup
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 将当前用户从 ChannelGroup 中移除
        channelGroup.remove(channelHandlerContext.channel());

        // 构造退出群聊的响应包
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(quitGroupRequestPacket.getGroupId());
        responsePacket.setSuccess(true);

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
