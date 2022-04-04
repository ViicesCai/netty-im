package edu.fdzc.im.common.util;

import edu.fdzc.im.common.data.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 登录工具类
 *
 * @author Viices Cai
 * @time 2022/3/7
 */
public class LoginUtil {

    /**
     * 登录标记
     *
     * @param channel Netty 通道
     */
    public static void markAsLogin(Channel channel) {

        channel.attr(Attributes.HAS_LOGIN).set(true);
    }

    /**
     * 是否登录
     *
     * @param channel 通讯通道
     * @return 是否登录
     */
    public static Boolean hasLogin(Channel channel) {
        Attribute<Boolean> hasLogin = channel.attr(Attributes.HAS_LOGIN);

        return hasLogin.get() != null;
    }

    private LoginUtil() { }
}
