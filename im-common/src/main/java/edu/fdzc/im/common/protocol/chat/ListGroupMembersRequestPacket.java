package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.protocol.Command;
import edu.fdzc.im.common.protocol.Packet;
import lombok.Data;

/**
 * 群聊人员列表请求包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
