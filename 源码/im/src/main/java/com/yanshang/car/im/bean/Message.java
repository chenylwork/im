package com.yanshang.car.im.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
 * @ClassName Message
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/9- 15:46
 * @Version 1.0
 **/
@Data
@Entity
@Table(name="t_message")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageid;
    @Column(name = "`from`")
    private String from; // 消息发送者（用户用户名）
    @Column(name = "`to`")
    private String to; // 消息接受者（房间号）
    @Column(name = "`type`")
    private String type; // 消息类型
    private String content; // 消息内容
    private String sendTime; // 消息发送时间
    @Column(name = "`version`")
    private int version; // 消息状态

}