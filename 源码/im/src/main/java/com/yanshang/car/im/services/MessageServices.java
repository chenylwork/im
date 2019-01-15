package com.yanshang.car.im.services;

import com.yanshang.car.im.bean.Message;
import com.yanshang.car.im.commons.NetMessage;

public interface MessageServices {

    /**
     * 添加消息信息
     * @param message
     * @return
     */
    NetMessage addMessage(Message message);

    /**
     * 获取用户的未读消息
     * @param username
     * @return
     */
    NetMessage getUnreadMessage(String username);





}
