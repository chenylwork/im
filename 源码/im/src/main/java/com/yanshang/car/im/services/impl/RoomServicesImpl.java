package com.yanshang.car.im.services.impl;

import com.yanshang.car.im.bean.Room;
import com.yanshang.car.im.bean.RoomUser;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.repositories.RoomRepository;
import com.yanshang.car.im.repositories.RoomUserRepository;
import com.yanshang.car.im.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.ch.Net;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;

/*
 * @ClassName RoomServicesImpl
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 9:52
 * @Version 1.0
 **/
@Service("roomServices")
public class RoomServicesImpl implements RoomServices {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomUserRepository roomUserRepository;

    @Override
    public NetMessage getRoom(int type, String name, String... usernames) {
        Room room = null;
        if (name == null) name = "";
        if (type == FRIEND) {
            if (usernames != null && usernames.length > 1) {
                Collections.sort(Arrays.asList(usernames));
                for (String username : usernames) name += username;
            } else {
                return NetMessage.failNetMessage("", "获取单聊房间需要至少两个用户的账户！！");
            }
        } else if (type == GROUP) {
            if (name.equals("")) return NetMessage.failNetMessage("", "获取群组房间需要房间名称参数！！");
            if (usernames == null || usernames.length < 1) return NetMessage.failNetMessage("", "获取群组房间至少需要一个用户账号！！");
        } else {
            return NetMessage.failNetMessage("","请输入正确的请求参数！！");
        }

        room = roomRepository.getByName(name);

        if (room == null) { // 创建新房间
            room = new Room();
            room.setName(name);
            room.setMessageCount(0);
            if (type == GROUP) room.setOwner(usernames[0]);
            try {
                Room save = roomRepository.save(room);
                for (String username : usernames) {
                    roomUserRepository.save(new RoomUser(username,save.getRoomid(),0));
                }
                return NetMessage.successNetMessage("", room);
            } catch (Exception e) {
                e.printStackTrace();
                return NetMessage.errorNetMessage();
            }
        } else {
            // 返回旧房间
            return NetMessage.successNetMessage("", room);
        }
    }
}
