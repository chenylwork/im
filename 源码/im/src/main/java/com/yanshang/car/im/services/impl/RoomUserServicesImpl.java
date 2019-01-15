package com.yanshang.car.im.services.impl;

import com.yanshang.car.im.bean.Room;
import com.yanshang.car.im.bean.RoomUser;
import com.yanshang.car.im.commons.NetMessage;
import com.yanshang.car.im.repositories.RoomRepository;
import com.yanshang.car.im.repositories.RoomUserRepository;
import com.yanshang.car.im.services.RoomServices;
import com.yanshang.car.im.services.RoomUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/*
 * @ClassName RoomUserServicesImpl
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 10:15
 * @Version 1.0
 **/
@Service("roomUserServices")
public class RoomUserServicesImpl implements RoomUserServices {
    @Autowired
    private RoomUserRepository roomUserRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public NetMessage addUser(String username, int roomid) {
        RoomUser roomUser = new RoomUser(username,roomid,0);
        try {
            Room one = roomRepository.getOne(roomid);
            if (one == null) throw new Exception("没有房间号为："+roomid+"的聊天室！！");
        } catch (Exception e) {
            return NetMessage.failNetMessage("","没有房间号为："+roomid+"的聊天室！！");
        }
        try {
            RoomUser save = roomUserRepository.save(roomUser);
            return NetMessage.successNetMessage("","成功加入房间");
        } catch (Exception e) {
            return NetMessage.errorNetMessage();
        }
    }

    @Override
    @Transactional
    public void changeCount(String username, int roomid) {
        roomUserRepository.changeCount(username,roomid);
    }

    @Override
    public List<RoomUser> getByRoomid(int roomid) {
        List<RoomUser> list = roomUserRepository.getByRoomid(roomid);
        return list;
    }
}
