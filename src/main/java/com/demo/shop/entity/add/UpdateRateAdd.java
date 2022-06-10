package com.demo.shop.entity.add;

import lombok.Data;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:12
 */
@Data
public class UpdateRateAdd {

    private String orderNumber;

//    private String serviceId;
//
//    private String detectCompany;
//    //检测对象
//    private String detectObject;
//    //检测项目
//    private String detectProject;
    //用户名
    private String userName;

    private int quality;

    private int speed;

    private int attitude;


    private String comment;
}
