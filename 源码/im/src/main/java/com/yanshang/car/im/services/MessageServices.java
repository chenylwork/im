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

    /**
     * 获取历史消息
     * @param roomid 聊天室编号
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    NetMessage getHistoryMessage(int roomid,String start,String end);





}
