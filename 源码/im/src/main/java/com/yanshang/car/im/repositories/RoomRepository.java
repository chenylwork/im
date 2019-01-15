package com.yanshang.car.im.repositories;

import com.yanshang.car.im.bean.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/*
 * @ClassName RoomRepository
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 9:26
 * @Version 1.0
 **/
@Repository
public interface RoomRepository extends JpaRepositoryImplementation<Room,Integer>{

    /**
     * 获取房间信息
     * @param name
     * @return
     */
    Room getByName(String name);

    /**
     * 改变消息总数
     * @param roomid
     * @return
     */
    @Modifying
    @Query("update Room t set t.messageCount = t.messageCount +1 where t.roomid = ?1")
    int changeCount(int roomid);

}
