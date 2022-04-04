package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.protocol.message.GroupMessageRequestPacket;
import edu.fdzc.im.common.protocol.message.GroupMessageResponsePacket;
import edu.fdzc.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 群聊消息请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/10
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    /**
     * 实例对象
     */
    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        // 获取 GroupID
        String toGroupId = groupMessageRequestPacket.getToGroupId();

        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(toGroupId);
        responsePacket.setMessage(groupMessageRequestPacket.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(channelHandlerContext.channel()));

        // 获取群聊对应的 channelGroup, 写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
