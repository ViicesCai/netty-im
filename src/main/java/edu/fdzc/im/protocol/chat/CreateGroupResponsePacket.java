package edu.fdzc.im.protocol.chat;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
import lombok.Data;

import java.util.List;

/**
 * 创建群聊响应包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private Boolean success;

    private String groupId;

    private List<String> userNames;

    @Override
    public Byte getCommand() {

        return Command.CREATE_GROUP_RESPONSE;
    }
}
