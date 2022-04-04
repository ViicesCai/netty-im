package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.common.protocol.chat.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 加入群聊
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner in, Channel channel) {
        System.out.print("输入 GroupID 加入群聊: ");
        String groupId = in.next();

        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        requestPacket.setGroupId(groupId);

        channel.writeAndFlush(requestPacket);
    }
}
