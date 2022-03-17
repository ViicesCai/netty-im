package edu.fdzc.im.server.handler;

import edu.fdzc.im.protocol.chat.CreateGroupRequestPacket;
import edu.fdzc.im.protocol.chat.CreateGroupResponsePacket;
import edu.fdzc.im.util.IDUtil;
import edu.fdzc.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建群聊请求控制器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    /**
     * 实例对象
     */
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() { }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIds = createGroupRequestPacket.getUserIds();

        List<String> userNames = new ArrayList<>();

        // 创建 Channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        // 筛选群聊包含的 user 和 channel
        for (String userId : userIds) {
            Channel channel = SessionUtil.getChannel(userId);

            if (channel != null) {
                channelGroup.add(channel);
                userNames.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        // 创建群聊的响应结果
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(IDUtil.randomUserId());
        responsePacket.setUserNames(userNames);

        // 向群聊中包含的客户端发送群聊通知
        channelGroup.writeAndFlush(responsePacket);

        System.out.print("群聊创建成功, id : [ " + responsePacket.getGroupId() + " ] ");
        System.out.println("群成员 : " + responsePacket.getUserNames());

        SessionUtil.bindChannelGroup(responsePacket.getGroupId(), channelGroup);
    }
}

