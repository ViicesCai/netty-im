package edu.fdzc.im.common.data;

import io.netty.util.AttributeKey;

/**
 * 常量属性
 *
 * @author Viices Cai
 * @time 2022/3/7
 */
public interface Attributes {

    /**
     * 是否登录
     */
    AttributeKey<Boolean> HAS_LOGIN = AttributeKey.newInstance("has_login");

    /**
     * 会话
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
