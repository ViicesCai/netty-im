package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;

/**
 * 退出群聊响应包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.QUIT_GROUP_RESPONSE;
    }
}
