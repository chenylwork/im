package com.yanshang.car.im.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/*
 * @ClassName Room
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/14- 9:17
 * @Version 1.0
 **/
@Entity
@Table(name="t_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Proxy(lazy = false)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomid; // 聊天室号
    private String name; // 聊天室名称(唯一标识)
    private String owner; // 聊天室创建者
    private Integer messageCount; // 总共消息数量
    private int userCount; // 用户总数
}
