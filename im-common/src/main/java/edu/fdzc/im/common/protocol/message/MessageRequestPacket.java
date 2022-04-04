package edu.fdzc.im.common.protocol.message;

import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息请求包
 *
 * @author Viices Cai
 * @time 2022/3/7
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket (String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {

        return Command.MESSAGE_REQUEST;
    }
}
