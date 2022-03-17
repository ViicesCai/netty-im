package edu.fdzc.im.protocol.message;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

/**
 * 群发消息请求包
 *
 * @author Viices Cai
 * @time 2022/3/10
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {

        return Command.GROUP_MESSAGE_REQUEST;
    }
}
