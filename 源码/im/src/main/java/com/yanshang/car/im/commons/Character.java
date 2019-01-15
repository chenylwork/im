package com.yanshang.car.im.commons;

import org.apache.commons.codec.digest.DigestUtils;

/*
 * @ClassName Character
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 10:58
 * @Version 1.0
 **/
public class Character {

    /**
     * 密码加密
     * @param password
     * @return
     */
    public static String passwordEncode(String password) {
        return DigestUtils.shaHex(password);
    }

    /**
     * 存储key加密
     * @param key
     * @return
     */
    public static String redisKeyEncode(String key) {
        return DigestUtils.shaHex(key);
    }

    public static String sendToAllToken(String str) {
        return DigestUtils.shaHex(str);
    }
}
