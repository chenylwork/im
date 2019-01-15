package com.yanshang.car.im.controller;

import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.repositories.MessageRepository;
import com.yanshang.car.im.services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @ClassName MessageController
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/15- 14:40
 * @Version 1.0
 **/
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageServices messageServices;

    /**
     * 获取历史聊天信息
     * @param roomid
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("/history")
    public NetMessage historyMessage(int roomid,String start,String end) {
        return messageServices.getHistoryMessage(roomid,start,end);
    }

}
