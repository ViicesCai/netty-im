package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;

import java.util.List;

/**
 * 创建群聊响应包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private Boolean success;

    private String groupId;

    private List<String> userIds;

    @Override
    public Byte getCommand() {

        return Command.CREATE_GROUP_REQUEST;
    }
}
