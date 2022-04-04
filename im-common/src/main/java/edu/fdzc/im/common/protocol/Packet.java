package edu.fdzc.im.common.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 数据包
 *
 * @author Viices Cai
 * @time 2022/3/4
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 指令
     *
     * @return 二进制
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
