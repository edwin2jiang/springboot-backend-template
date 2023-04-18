package com.taikven.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
评论
 *
 * 
 * @since 2023-04-11
 */
@Data
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "hid", type = IdType.AUTO)
    private Integer hid;

    private Integer uid;

    private String name;

    private String content;

    private Double price;

    private String address;

    private String province;

    private String city;

    private String area;

    @TableLogic(value = "0",delval = "1")
    private Boolean deleted;

    @TableField(exist = false)
    private List<Photo> photos;
}
