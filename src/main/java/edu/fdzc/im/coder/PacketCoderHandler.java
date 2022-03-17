package edu.fdzc.im.coder;

import edu.fdzc.im.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * 包编码控制器
 *
 * @author Viices Cai
 * @time 2022/3/10
 */
@ChannelHandler.Sharable
public class PacketCoderHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    /**
     * 实例对象
     */
    public static final PacketCoderHandler INSTANCE = new PacketCoderHandler();

    private PacketCoderHandler() { }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().ioBuffer();
        PacketCoder.getInstance().encode(byteBuf, packet);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCoder.getInstance().decode(byteBuf));
    }
}
