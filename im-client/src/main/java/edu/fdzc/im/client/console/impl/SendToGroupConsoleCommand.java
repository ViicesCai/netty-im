package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.common.protocol.message.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 群发消息
 *
 * @author Viices Cai
 * @time 2022/3/10
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner in, Channel channel) {
        System.out.print("发送消息给指定群组: ");

        String toGroupId = in.next();
        String message = in.next();

        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
