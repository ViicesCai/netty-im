package edu.fdzc.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令接口
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public interface ConsoleCommand {

    /**
     * 登录
     */
    String LOGIN = "login";

    /**
     * 退出登录
     */
    String LOGOUT = "logout";

    /**
     * 发送消息给指定用户
     */
    String SEND_TO_USER = "sendToUser";

    /**
     * 创建群聊
     */
    String CREATE_GROUP = "createGroup";

    /**
     * 加入群聊
     */
    String JOIN_GROUP = "joinGroup";

    /**
     * 退出群聊
     */
    String QUIT_GROUP = "quitGroup";

    /**
     * 群组成员列表
     */
    String LIST_GROUP_MEMBERS = "listGroupMembers";

    /**
     * 发送消息给指定群聊
     */
    String SEND_TO_GROUP = "sendToGroup";

    /**
     * 执行方法
     *
     * @param in 输入流
     * @param channel 通信通道
     */
    void execute(Scanner in, Channel channel);
}
