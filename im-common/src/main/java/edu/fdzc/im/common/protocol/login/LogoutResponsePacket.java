package edu.fdzc.im.common.protocol.login;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
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
