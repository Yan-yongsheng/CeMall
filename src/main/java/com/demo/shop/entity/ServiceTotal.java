package com.demo.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("service_total")
public class ServiceTotal implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String categoryOne;

    private String categoryTwo;

    private String categoryThree;

    private String serviceTitle;

    private String serviceUrl;

    private String detectionItem;

    private String institutionTitle;

    private String institutionUrl;

    private String serviceDetail;

    private String price;

    private String cycle;

    private String serviceScore;

    private String qualityScore;

    private String speedScore;

    private String attitudeScore;
}
