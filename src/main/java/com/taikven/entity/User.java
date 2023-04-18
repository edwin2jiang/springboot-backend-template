package com.taikven.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * 评论
 * 
 * @since 2023-04-11
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private String username;

    private String name;

    private String phone;

    private String password;

    private Integer type;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
        "uid = " + uid +
        ", username = " + username +
        ", name = " + name +
        ", phone = " + phone +
        ", password = " + password +
        ", type = " + type +
        "}";
    }
}
