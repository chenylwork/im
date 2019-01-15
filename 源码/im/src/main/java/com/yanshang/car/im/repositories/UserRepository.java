package com.yanshang.car.im.repositories;

import com.yanshang.car.im.bean.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/*
 * @ClassName UserRepository
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 10:06
 * @Version 1.0
 **/
@Repository
public interface UserRepository extends JpaRepositoryImplementation<User,Integer> {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User getByUsername(String username);
}
