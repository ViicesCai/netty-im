package edu.fdzc.im.client.console.impl;

import edu.fdzc.im.client.console.ConsoleCommand;
import edu.fdzc.im.common.protocol.login.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 登录
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner in, Channel channel) {
        LoginRequestPacket requestPacket = new LoginRequestPacket();

        System.out.print("请输入用户名: ");
        requestPacket.setUserName(in.nextLine());
        requestPacket.setPassword("admin");

        // 发送登录数据包
        channel.writeAndFlush(requestPacket);

        waitForResponse();
    }

    /**
     * 等待登录响应
     */
    private static void waitForResponse() {
        try {
            TimeUnit.SECONDS.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
