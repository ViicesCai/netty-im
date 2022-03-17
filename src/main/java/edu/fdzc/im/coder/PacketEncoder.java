package edu.fdzc.im.coder;

import edu.fdzc.im.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 数据包编码器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

        PacketCoder.getInstance().encode(byteBuf, packet);
    }
}
