package com.demo.shop.entity.add;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:12
 */
@Data
public class RatingUploadAdd {


    private String detectCompany;
    //检测对象
    private String detectObject;
    //检测项目
    private String detectProject;
    //用户名
    private String userName;

    private int qualityScore;

    private int speedScore;

    private int attitudeScore;


    private String comment;
}
