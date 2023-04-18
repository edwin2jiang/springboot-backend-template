package com.taikven.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
评论
 *
 * 
 * @since 2023-04-11
 */
@Data
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    private Integer hid;

    private Integer uid;

    private Integer status;

    private BigDecimal price;

    @TableField("start_date")
    private Date startDate;
    @TableField("end_date")
    private Date endDate;


}
