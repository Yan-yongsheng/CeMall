package com.demo.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:12
 */
@Data
@TableName("rating_total")
public class RatingTotal implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

//    //订单号
//    private int orderId;

    private String detectCompany;
    //检测对象
    private String detectObject;
    //检测项目
    private String detectProject;
    //用户名
    private String userName;

    private int qualityScore;

    private int speedScore;

    private int attitudeScore;

    private Date createTime;

    private Date updateTime;

    private String comment;
}
