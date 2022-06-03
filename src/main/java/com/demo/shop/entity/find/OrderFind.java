package com.demo.shop.entity.find;

import lombok.Data;

import java.util.Date;

@Data
public class OrderFind {
    //展示订单的类
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
    //下单时间
    private Date createTime;
}
