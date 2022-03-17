package edu.fdzc.im.protocol.login;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

/**
 * 退出登录响应包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class LogoutResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.LOGOUT_RESPONSE;
    }
}
