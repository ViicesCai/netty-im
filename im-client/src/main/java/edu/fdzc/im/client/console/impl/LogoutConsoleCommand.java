package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.common.data.Session;
import edu.fdzc.im.common.protocol.login.LogoutRequestPacket;
import edu.fdzc.im.common.util.SessionUtil;
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
        Session session = SessionUtil.getSession(channel);

        LogoutRequestPacket requestPacket = new LogoutRequestPacket();
        requestPacket.setName(session.getUserName());

        channel.writeAndFlush(requestPacket);
    }
}
