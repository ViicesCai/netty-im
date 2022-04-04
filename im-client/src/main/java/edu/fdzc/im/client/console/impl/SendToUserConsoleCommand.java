package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.common.protocol.message.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送消息给指定用户
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner in, Channel channel) {
        System.out.print("发送消息给指定用户: ");

        String toUserId = in.next();
        String message = in.next();

        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
