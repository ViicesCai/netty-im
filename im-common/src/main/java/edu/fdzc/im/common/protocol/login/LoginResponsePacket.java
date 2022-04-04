package edu.fdzc.im.common.protocol.login;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;

/**
 * 登录响应包
 *
 * @author Viices Cai
 * @time 2022/3/4
 */
@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.LOGIN_RESPONSE;
    }
}
