package com.demo.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("service")
public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    //service_id  detect_company detect_object  detect_project
    // detect_price  detect_time  detect_score detect_standard  updateTime
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
    //服务分数，后期会根据评价更新，可为公司评分或者服务评分
    private double detectScore;

    private Date createTime;

    private Date updateTime;


}
