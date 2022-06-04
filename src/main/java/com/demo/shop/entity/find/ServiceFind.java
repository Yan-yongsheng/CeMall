package com.demo.shop.entity.find;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "cemall", type = "ServiceFind")
public class ServiceFind {
    //服务Id 自动生成 时间+公司
    @Id
    private String serviceId;

    private String detectCompany;
    //检测对象
    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String detectObject;
    //检测项目
    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_smart")
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
