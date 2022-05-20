package com.demo.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:31
 */
@Data
@TableName("user")
public class User {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String userId;

    private String account;
    private String password;
    //账号类别 1 用户 2 商家 3 专家 4 管理员不能任意注册
    private String category;


    private String phone;

    private String mail;
}
