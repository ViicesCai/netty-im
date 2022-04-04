package edu.fdzc.im.server.handler;

import edu.fdzc.im.common.data.Session;
import edu.fdzc.im.common.protocol.chat.ListGroupMembersRequestPacket;
import edu.fdzc.im.common.protocol.chat.ListGroupMembersResponsePacket;
import edu.fdzc.im.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 群组用户列表请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    /**
     * 实例对象
     */
    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        // 获取群聊的 GroupId
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 构造群成员信息
        List<Session> sessions = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessions.add(session);
        }

        // 构造响应包
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSessions(sessions);

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
