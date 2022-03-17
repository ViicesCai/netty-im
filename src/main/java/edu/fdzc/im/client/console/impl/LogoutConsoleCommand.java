package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.protocol.login.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 退出登录
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class LogoutConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner in, Channel channel) {
        LogoutRequestPacket requestPacket = new LogoutRequestPacket();

        channel.writeAndFlush(requestPacket);
    }
}
