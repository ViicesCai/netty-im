package edu.fdzc.im.common.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 拆包器
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    /**
     * 首部偏移量
     */
    private static final int LENGTH_FIELD_OFFSET = 7;

    /**
     * 数据长度
     */
    private static final int LENGTH_FIELD_LENGTH = 4;


    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        // 过滤非本协议的数据报文
        if (in.readableBytes() >= Integer.BYTES
                && in.getInt(in.readerIndex()) != PacketCoder.MAGIC_NUMBER) {
            System.err.println("非法的请求，连接中断!");
            ctx.channel().close();

            return null;
        }

        return super.decode(ctx, in);
    }
}
