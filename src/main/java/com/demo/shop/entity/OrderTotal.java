package com.demo.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:12
 */
@Data
@TableName("order_total")
public class OrderTotal implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String orderNumber;

    private String serviceId;

    private String detectCompany;
    //检测对象
    private String detectObject;
    //检测项目
    private String detectProject;
    //检测价格
    private double detectPrice;
    //检测时间 day
    private int detectTime;
    //检测标准
    private String detectStandard;
    //用户名
    private String userName;
    //以下几个方便扩展，1.0版本不展示

    private int qualityScore;

    private int speedScore;

    private int attitudeScore;

    private Date createTime;

    private Date updateTime;
    //评论
    private String commentNew;
}
