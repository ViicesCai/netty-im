package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.protocol.chat.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 创建群聊
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    /**
     * 用户 ID 分隔符
     */
    private static final String USER_ID_SPLITER = ",";

    @Override
    public void execute(Scanner in, Channel channel) {
        CreateGroupRequestPacket requestPacket = new CreateGroupRequestPacket();

        System.out.print("[创建群聊] 输入 UserID 列表，UserID 间使用英文逗号 ',' 隔开: ");

        String userIds = in.next();
        requestPacket.setUserIds(Arrays.asList(userIds.split(USER_ID_SPLITER)));

        channel.writeAndFlush(requestPacket);
    }
}
