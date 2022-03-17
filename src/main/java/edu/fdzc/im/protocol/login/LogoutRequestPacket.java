package edu.fdzc.im.protocol.login;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

/**
 * 退出登录请求包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {

        return Command.LOGOUT_REQUEST;
    }
}
