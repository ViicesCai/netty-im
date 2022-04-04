package edu.fdzc.im.common.util;

import edu.fdzc.im.common.data.Attributes;
import edu.fdzc.im.common.data.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话工具类
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
public class SessionUtil {

    /**
     * 用户会话集
     */
    private static final Map<String, Channel> userChannels = new ConcurrentHashMap<>();

    /**
     * 群组会话集
     */
    private static final Map<String, ChannelGroup> groupChannels = new ConcurrentHashMap<>();

    /**
     * 绑定会话
     *
     * @param session 会话
     * @param channel 通信通道
     */
    public static void bindSession(Session session, Channel channel) {
        userChannels.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 取消会话
     *
     * @param channel 通信通道
     */
    public static void unBindSession(Channel channel) {
        if (LoginUtil.hasLogin(channel)) {
            userChannels.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    /**
     * 获取通道对应的 Session
     *
     * @param channel 通信通道
     * @return 会话
     */
    public static Session getSession(Channel channel) {

        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 获取用户对应的通道
     *
     * @param userId 用户 ID
     * @return 通信通道
     */
    public static Channel getChannel(String userId) {

        return userChannels.get(userId);
    }

    /**
     * 绑定通道群组
     *
     * @param groupId 群组 ID
     * @param channelGroup 通道群组
     */
    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {

        groupChannels.put(groupId, channelGroup);
    }

    /**
     * 获取群组对应的通道群组
     *
     * @param groupId 群组 ID
     * @return 通道群组
     */
    public static ChannelGroup getChannelGroup(String groupId) {

        return groupChannels.get(groupId);
    }

    private SessionUtil() { }
}
