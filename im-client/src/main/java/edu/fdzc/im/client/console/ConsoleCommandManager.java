package edu.fdzc.im.client.console;

import edu.fdzc.im.client.console.impl.*;
import edu.fdzc.im.common.util.LoginUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 控制台命令管理器
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class ConsoleCommandManager implements ConsoleCommand {

    /**
     * 控制台命令映射集
     */
    private final Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();

        consoleCommandMap.put(ConsoleCommand.SEND_TO_USER, new SendToUserConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.LOGOUT, new LogoutConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.CREATE_GROUP, new CreateGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.JOIN_GROUP, new JoinGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.QUIT_GROUP, new QuitGroupConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.LIST_GROUP_MEMBERS, new ListGroupMembersConsoleCommand());
        consoleCommandMap.put(ConsoleCommand.SEND_TO_GROUP, new SendToGroupConsoleCommand());
    }

    @Override
    public void execute(Scanner in, Channel channel) {
        // 接收指令
        String command = in.next();

        if (!LoginUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.execute(in, channel);

        } else {
            System.err.println("未知指令 [ " + command + " ], 请重新输入!" );
        }
    }
}
