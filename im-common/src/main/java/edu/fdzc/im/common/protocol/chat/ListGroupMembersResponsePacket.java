package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.data.Session;
import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;

import java.util.List;

/**
 * 群聊人员列表响应包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessions;

    @Override
    public Byte getCommand() {

        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
