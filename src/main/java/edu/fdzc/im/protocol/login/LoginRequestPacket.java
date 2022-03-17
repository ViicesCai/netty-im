package edu.fdzc.im.protocol.login;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

/**
 * 登录请求包
 *
 * @author Viices Cai
 * @time 2022/3/4
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return Command.LOGIN_REQUEST;
    }
}
