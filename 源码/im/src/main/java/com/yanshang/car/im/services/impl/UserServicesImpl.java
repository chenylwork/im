package com.yanshang.car.im.services.impl;

import com.yanshang.car.im.bean.User;
import com.yanshang.car.im.commons.Character;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.repositories.UserRepository;
import com.yanshang.car.im.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @ClassName UserServicesImpl
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 10:08
 * @Version 1.0
 **/
@Service("UserServices")
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkUserRegister(String username) {
        return userRepository.getByUsername(username) != null;
    }

    @Override
    public NetMessage register(User user) {
        if (userRepository.getByUsername(user.getUsername()) != null) {
            return NetMessage.failNetMessage("","该用户已经创建");
        }
        try {
            user.setPassword(Character.passwordEncode(user.getPassword()));
            userRepository.save(user);
            System.out.println("注册成功");
            return NetMessage.successNetMessage("","注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册失败");
            return NetMessage.failNetMessage("","注册失败");
        }
    }
}
