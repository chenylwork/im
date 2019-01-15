package com.yanshang.car.im.controller;

import com.yanshang.car.im.bean.Room;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.services.RoomServices;
import com.yanshang.car.im.services.RoomUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @ClassName RoomController
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 11:25
 * @Version 1.0
 **/
@RestController
@RequestMapping("/room")
public class RoomController {
    private static final int GROUP = 1;
    private static final int FRIEND = 2;

    @Autowired
    private RoomServices roomServices;
    @Autowired
    private RoomUserServices roomUserServices;

    /**
     * 获取聊天室房间
     * @param select  房间类型 群组：{@link #GROUP},一对一对话{@link #FRIEND}
     * @param name 房间名称（群组聊天室使用）
     * @param usernames 房间成员（一对一聊天使用）
     * @return
     */
    @RequestMapping("/get")
    public NetMessage addRoom(int select,String name,String... usernames) {
        return roomServices.getRoom(select,name,usernames);

    }

    @RequestMapping("/into")
    public NetMessage intoRoom(String username,int roomid) {
        NetMessage netMessage = roomUserServices.addUser(username, roomid);
        return  netMessage;
    }

}
