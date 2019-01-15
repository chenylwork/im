package com.yanshang.car.im.services;

import com.yanshang.car.im.bean.RoomUser;
import com.yanshang.car.im.commons.NetMessage;

import java.util.List;

/**
 * 房间和用户关系操作
 */
public interface RoomUserServices {

    /**
     * 用户进入房间，添加关系
     * @param username
     * @param roomid
     * @return
     */
    NetMessage addUser(String username, int roomid);

    /**
     * 修改已读消息数量
     * @param username
     * @param roomid
     */
    void changeCount(String username,int roomid);

    /**
     * 根据房间编号，获取用户名集合
     * @param roomid
     * @return
     */
    List<RoomUser> getByRoomid(int roomid);
}
