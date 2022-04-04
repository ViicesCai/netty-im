package edu.fdzc.im.common.protocol.message;

import edu.fdzc.im.common.protocol.Command;
import edu.fdzc.im.common.protocol.Packet;
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
