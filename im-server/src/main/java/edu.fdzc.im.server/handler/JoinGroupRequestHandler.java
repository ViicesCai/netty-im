package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.protocol.chat.JoinGroupRequestPacket;
import edu.fdzc.im.common.protocol.chat.JoinGroupResponsePacket;
import edu.fdzc.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 加入群聊请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    /**
     * 实例对象
     */
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();

        // 获取对应的 ChannelGroup
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        // 将当前用户的 Channel 加入
        channelGroup.add(channelHandlerContext.channel());

        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSuccess(true);

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
