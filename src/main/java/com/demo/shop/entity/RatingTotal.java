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
    //比订单更细分 一笔订单中的可拆分的每一个服务可以当作一个新的
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    //订单号
    private int orderId;

    private String serviceId;


    private String userId;

    private String price;

    private String cycle;

    private String serviceScore;

    private String qualityScore;

    private String speedScore;

    private String attitudeScore;

    private Date createTime;

    private Date completeTime;

    private String comment;
}
