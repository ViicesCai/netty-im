package edu.fdzc.im.protocol.message;

import edu.fdzc.im.data.Session;
import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

/**
 * 群发消息响应包
 *
 * @author Viices Cai
 * @time 2022/3/10
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return Command.GROUP_MESSAGE_RESPONSE;
    }
}
