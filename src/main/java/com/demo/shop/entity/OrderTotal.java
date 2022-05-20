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
//    //不太对，因为一笔订单可能包括多个服务
//    //使用list
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    private String orderId;

//    private List<String> serviceIdList;

    private String serviceId;

//    private String serviceDetail;
//
//    private String organizationId;

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
