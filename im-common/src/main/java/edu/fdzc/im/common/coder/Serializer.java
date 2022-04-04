package edu.fdzc.im.common.coder;

import edu.fdzc.im.common.coder.impl.JSONSerializerImpl;

/**
 * 序列化接口
 *
 * @author Viices Cai
 * @time 2022/3/4
 */
public interface Serializer {

    /**
     * JSON 序列化
     */
    byte JSON_SERIALIZER = SerializerAlgorithm.JSON;

    /**
     * 默认序列化方式
     */
    Serializer DEFAULT = new JSONSerializerImpl();

    /**
     * 获取序列化算法
     *
     * @return 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     *
     * @param object 对象
     * @return 二进制数据
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     *
     * @param clazz 对象
     * @param bytes 二进制数据
     * @param <T> 类
     * @return java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
