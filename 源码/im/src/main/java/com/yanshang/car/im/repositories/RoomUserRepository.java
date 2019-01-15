package com.yanshang.car.im.repositories;

import com.yanshang.car.im.bean.RoomUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomUserRepository extends JpaRepositoryImplementation<RoomUser,Integer> {

    /**
     * 修改已读数量
     */
    @Modifying
    @Query(value = "update RoomUser t set t.count = t.count + 1 where t.username = ?1 and t.roomid = ?2")
    void changeCount(String username,int roomid);

    /**
     * 根据房间编号获取，该房间的用户
     * @param roomid
     * @return
     */
    List<RoomUser> getByRoomid(int roomid);

    /**
     * 根据发送者用户和接收者房间号获取实体
     * @param username
     * @param roomid
     * @return
     */
    RoomUser getByUsernameAndRoomid(String username,int roomid);
}
