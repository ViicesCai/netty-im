package edu.fdzc.im.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会话
 *
 * @author Viices Cai
 * @time 2022/3/8
 */
@Data
@NoArgsConstructor
public class Session {

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + " : " + userName;
    }
}
