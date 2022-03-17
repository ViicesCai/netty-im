package edu.fdzc.im.protocol.chat;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

/**
 * 加入群聊响应包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.JOIN_GROUP_RESPONSE;
    }
}
