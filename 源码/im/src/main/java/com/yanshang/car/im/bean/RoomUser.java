package com.yanshang.car.im.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
 * @ClassName RoomUser
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 9:18
 * @Version 1.0
 **/
@Entity
@Table(name="t_room_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 编号
    private String username; // 用户编号
    private Integer roomid; // 房间编号
    @Column(name = "`count`")
    private Integer count; // 已读消息数量

    public RoomUser(String username, Integer roomid, Integer count) {
        this.username = username;
        this.roomid = roomid;
        this.count = count;
    }
}
