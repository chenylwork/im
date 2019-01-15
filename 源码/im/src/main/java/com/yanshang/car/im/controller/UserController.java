package com.yanshang.car.im.controller;

import com.yanshang.car.im.bean.User;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @ClassName UserController
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 11:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @RequestMapping("/register")
    public NetMessage register(User user) {
        NetMessage register = userServices.register(user);
        return register;
    }
}
