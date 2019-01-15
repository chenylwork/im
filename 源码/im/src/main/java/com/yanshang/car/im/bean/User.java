package com.yanshang.car.im.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
 * @ClassName User
 * @Description 作用描述
 * @Author 陈彦磊
 * @Date 2019/1/11- 8:46
 * @Version 1.0
 **/
@Entity
@Table(name="t_user",
        uniqueConstraints = {@UniqueConstraint(columnNames="username")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID; // 用户编号
    private String username; // 账号
    private String password; // 密码
    private String nickname; // 昵称
    private String type; // 用户类型

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
