package com.taikven.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
评论
 *
 * 
 * @since 2023-04-11
 */
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    private Integer hid;

    private Integer uid;

    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo{" +
        "pid = " + pid +
        ", hid = " + hid +
        ", uid = " + uid +
        ", url = " + url +
        "}";
    }
}
