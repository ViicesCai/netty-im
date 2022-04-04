package edu.fdzc.im.common.coder;

import edu.fdzc.im.common.coder.impl.JSONSerializerImpl;
import edu.fdzc.im.common.protocol.Command;
import edu.fdzc.im.common.protocol.Packet;
import edu.fdzc.im.common.protocol.chat.*;
import edu.fdzc.im.common.protocol.login.LoginRequestPacket;
import edu.fdzc.im.common.protocol.login.LoginResponsePacket;
import edu.fdzc.im.common.protocol.login.LogoutRequestPacket;
import edu.fdzc.im.common.protocol.login.LogoutResponsePacket;
import edu.fdzc.im.common.protocol.message.GroupMessageRequestPacket;
import edu.fdzc.im.common.protocol.message.GroupMessageResponsePacket;
import edu.fdzc.im.common.protocol.message.MessageRequestPacket;
import edu.fdzc.im.common.protocol.message.MessageResponsePacket;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据包编码器
 *
 * @author Viices Cai
 * @time 2022/3/4
 */
public class PacketCoder {

    /**
     * 实例对象
     */
    private volatile static PacketCoder instance;

    /**
     * 魔数:协议包规范
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 包类型映射关系
     */
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;

    /**
     * 序列化映射关系
     */
    private final Map<Byte, Serializer> serializerMap;

    private PacketCoder() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        packetTypeMap.put(Command.HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        packetTypeMap.put(Command.HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializerImpl();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public static PacketCoder getInstance() {
        if (instance == null) {
            synchronized (PacketCoder.class) {
                if (instance == null) {
                    instance = new PacketCoder();
                }
            }
        }

        return instance;
    }

    /**
     * 编码
     *
     * @param byteBuf 内存对象
     * @param packet 数据包
     */
    public void encode(ByteBuf byteBuf, Packet packet) {

        // 序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    /**
     * 译码
     *
     * @param byteBuf 字节缓冲区
     * @return Packet 对象
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int len = byteBuf.readInt();

        byte[] data = new byte[len];
        byteBuf.readBytes(data);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, data);
        }

        return null;
    }

    /**
     * 获取序列化对象
     *
     * @param serializerAlgorithm 序列化算法
     * @return 序列化对象
     */
    private Serializer getSerializer(byte serializerAlgorithm) {

        return serializerMap.get(serializerAlgorithm);
    }

    /**
     * 获取请求类型
     *
     * @param command 指令对象
     * @return 请求类型
     */
    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
