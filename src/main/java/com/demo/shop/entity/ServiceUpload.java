package com.demo.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("service_upload")
public class ServiceUpload {
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

    private String serviceDetail;

    private String standard;

    private String equipment;

    private Date createTime;
}
