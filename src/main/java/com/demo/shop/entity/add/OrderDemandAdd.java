package com.demo.shop.entity.add;

import lombok.Data;

@Data
public class OrderDemandAdd {
    //提交新订单的类
    private String userId;
    private String serviceId;
    private String name;

    private String price;

    private String cycle;
}
