package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.protocol.Command;
import edu.fdzc.im.common.protocol.Packet;
import lombok.Data;

/**
 * 加入群聊请求包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return Command.JOIN_GROUP_REQUEST;
    }
}
