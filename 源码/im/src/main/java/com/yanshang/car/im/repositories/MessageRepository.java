package com.yanshang.car.im.repositories;

import com.yanshang.car.im.bean.Message;
import com.yanshang.car.im.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @ClassName MessageRepository
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/9- 15:47
 * @Version 1.0
 **/
@Repository
public interface MessageRepository  extends JpaRepositoryImplementation<Message,Integer>{
    String UNREAD = "0";
    String READ = "1";

//    List<Message> getByUsername(String username);

    @Query(value = "select * from t_message a ,t_room_user b " +
            "where a.`to` = b.roomid and b.username = ?1 and a.version > b.`count` and a.`from` != ?1 ",nativeQuery = true)
    List<Message> getUnreadMessage(String username);

    @Query(value = "select * from t_message where " +
            "`to`= ?1 and  send_time >= ?2 AND send_time <= ?3 ORDER BY `version`",nativeQuery = true)
    List<Message> getHistoryMessage(int roomid,String start,String end);
}
