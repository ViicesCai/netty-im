package edu.fdzc.im.coder.impl;

import com.alibaba.fastjson.JSON;
import edu.fdzc.im.coder.Serializer;
import edu.fdzc.im.coder.SerializerAlgorithm;

/**
 * JSON 序列化实现
 *
 * @author Viices Cai
 * @time 2022/3/4
 */
public class JSONSerializerImpl implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {

        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
