package com.yanshang.car.im;

import org.apache.commons.codec.digest.DigestUtils;

/*
 * @ClassName Maintest
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 11:45
 * @Version 1.0
 **/
public class Maintest {

    public static void main(String[] a) {
        String code = DigestUtils.shaHex("99865");

        System.out.println(code);
    }
}
