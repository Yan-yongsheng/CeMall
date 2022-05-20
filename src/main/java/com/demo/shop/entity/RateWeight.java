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
@TableName("weight_total")
public class RateWeight implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;


    private String expertId;

    private String name;

    private String phone;

    private String mail;

    private String quality;

    private String speed;

    private String attitude;

    private String service;

    private String organization;

    private Date createTime;

}
