package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;

/**
 * 退出群聊请求包
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return Command.QUIT_GROUP_REQUEST;
    }
}
