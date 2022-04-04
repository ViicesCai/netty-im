package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.common.protocol.chat.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 群组成员列表
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner in, Channel channel) {
        System.out.print("输入 GroupId, 获取群成员列表: ");

        String groupId = in.next();

        ListGroupMembersRequestPacket requestPacket = new ListGroupMembersRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
