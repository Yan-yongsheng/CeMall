package com.demo.shop.entity.find;

import lombok.Data;

@Data
public class ServiceFind {
    //服务Id 自动生成 时间+公司
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
    //查看需要
    //服务分数，后期会根据评价更新，可为公司评分或者服务评分
    private double detectScore;
}
