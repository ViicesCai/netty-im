package edu.fdzc.im.common.protocol.chat;

import edu.fdzc.im.common.protocol.Command;
import edu.fdzc.im.common.protocol.Packet;

/**
 * 心跳请求包
 *
 * @author Viices Cai
 * @time 2022/3/23
 */
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {

        return Command.HEARTBEAT_REQUEST;
    }
}
