package com.yanshang.car.im.services;

import com.yanshang.car.im.bean.User;
import com.yanshang.car.im.commons.NetMessage;

/*
 * @ClassName UserServices
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 10:08
 * @Version 1.0
 **/
public interface UserServices {
    /**
     * 根据用户名检查用户是否存在
     * @param username
     * @return
     */
    boolean checkUserRegister(String username);

    /**
     * 注册用户
     * @param user
     * @return
     */
    NetMessage register(User user);
}
