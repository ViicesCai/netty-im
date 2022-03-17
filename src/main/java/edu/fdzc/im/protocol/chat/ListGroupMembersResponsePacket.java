package edu.fdzc.im.protocol.chat;

import edu.fdzc.im.data.Session;
import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
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
