package edu.fdzc.im.common.protocol.login;

import edu.fdzc.im.common.protocol.Command;
import edu.fdzc.im.common.protocol.Packet;
import lombok.Data;

/**
 * 退出登录请求包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class LogoutRequestPacket extends Packet {

    private String name;

    @Override
    public Byte getCommand() {

        return Command.LOGOUT_REQUEST;
    }
}
