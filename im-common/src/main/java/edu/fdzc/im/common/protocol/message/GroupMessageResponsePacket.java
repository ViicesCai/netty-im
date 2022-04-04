package edu.fdzc.im.common.protocol.message;

import edu.fdzc.im.common.data.Session;
import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
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
