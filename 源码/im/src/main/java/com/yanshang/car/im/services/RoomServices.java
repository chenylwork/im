package com.yanshang.car.im.services;

import com.yanshang.car.im.bean.Room;
import com.yanshang.car.im.commons.NetMessage;

/*
 * @ClassName RoomServices
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 9:29
 * @Version 1.0
 **/
public interface RoomServices {
    int GROUP = 1;
    int FRIEND= 2;
    /**
     * 获取群聊房间
     * @param name 群名称
     * @return
     */
    NetMessage getRoom(int type,String name,String... usernames);
}
