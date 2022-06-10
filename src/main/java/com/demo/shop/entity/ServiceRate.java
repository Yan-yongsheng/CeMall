package com.demo.shop.entity;

import lombok.Data;

/**
 * @Author: yys
 * @Date: 2022/5/12 19:38
 */

@Data
public class ServiceRate {
    //用于暂存service和三项评分
    //服务id
    private String serviceId;
    //质量评分
    private double qualityScore;

    //速度评分
    private double speedScore;

    //态度评分
    private double attitudeScore;
}
