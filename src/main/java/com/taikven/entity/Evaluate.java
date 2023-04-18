package com.taikven.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @since 2023-04-11
 */
@Data
public class Evaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;
    private Integer uid;
    private Integer hid;
    private Integer oid;

    private String content;

    private float star;

    private Date date;


    @Override
    public String toString() {
        return "Evaluate{" +
                "eid=" + eid +
                ", uid=" + uid +
                ", hid=" + hid +
                ", oid=" + oid +
                ", content=" + content +
                ", date=" + date +
                "}";
    }
}
