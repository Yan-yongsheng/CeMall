package com.demo.shop.entity.add;

import lombok.Data;

@Data
public class OrderDemandAdd {
    //提交新订单的类
    private String detectCompany;

    private String serviceId;
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
}
