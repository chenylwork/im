package com.yanshang.car.im.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
 * @ClassName RedisUtils
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 14:44
 * @Version 1.0
 **/
@Component("redisUtils")
public class RedisUtils {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public RedisTemplate<String,Object> getRedisTemplate() {
        return redisTemplate;
    }

}
