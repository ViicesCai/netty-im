package edu.fdzc.im.common.protocol.message;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;

/**
 * 消息响应包
 *
 * @author Viices Cai
 * @time 2022/3/7
 */
@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return Command.MESSAGE_RESPONSE;
    }
}
