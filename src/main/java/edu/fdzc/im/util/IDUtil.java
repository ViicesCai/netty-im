package edu.fdzc.im.util;

import java.util.UUID;

/**
 * ID 工具类
 *
 * @author Viices Cai
 * @time 2022/3/9
 */
public class IDUtil {

    /**
     * 随机生成用户 ID
     *
     * @return 用户 ID
     */
    public static String randomUserId() {

        return UUID.randomUUID().toString().split("-")[0];
    }

    private IDUtil() { }
}
