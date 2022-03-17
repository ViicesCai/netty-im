package edu.fdzc.im.protocol.chat;

import edu.fdzc.im.protocol.Command;
import edu.fdzc.im.protocol.Packet;
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
