package com.demo.shop.entity.find;

import lombok.Data;

@Data
public class CategoryContent {
    //主键ID
    public Integer id;

    //检测主体
    public String categoryThree;

    //服务机构
    public String institutionTitle;

    //服务名称
    public String serviceTitle;

    //检测项目
    public String detectionItem;

    //服务详情
    public String serviceUrl;

    //服务描述
    public String serviceDetail;

    //质量评分
    public Integer qualityScore;

    //速度评分
    public Integer speedScore;

    //态度评分
    public Integer attitudeScore;

    //价格
    public String price;

    //时间
    public String cycle;
}
