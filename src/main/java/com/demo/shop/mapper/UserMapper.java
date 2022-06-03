package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.Service;
import com.demo.shop.entity.find.OrderFind;
import com.demo.shop.entity.find.ServiceFind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:31
 */
@Mapper
public interface UserMapper extends BaseMapper<OrderTotal> {
    int register(String userId,String account, String password,String category);

    int login(String account, String password,String category);

    void makeOrder(@Param("orderNumber") String orderNumber,@Param("serviceId") String serviceId,@Param("detectCompany") String detectCompany,@Param("detectObject") String detectObject,
                   @Param("detectProject") String detectProject, @Param("detectPrice") double detectPrice,@Param("detectTime") int detectTime,
                   @Param("detectStandard") String detectStandard,@Param("userName") String userName,
                   @Param("createTime") Date createTime,@Param("updateTime") Date updateTime);
    void completeOrder(String orderId, String serviceId, String userId,Date completeTime);
    List<ServiceFind> allService(String detectObject,String detectProject);
    List<OrderFind> myOrder(@Param("userName") String userName);
    //用户提交对某项服务的评分

    void submitComment(@Param("orderNumber") String orderNumber,@Param("serviceId") String serviceId,@Param("detectCompany") String detectCompany,@Param("detectObject") String detectObject,
                       @Param("detectProject") String detectProject, @Param("userName") String userName,
                       @Param("qualityScore") int qualityScore, @Param("speedScore") int speedScore,
                       @Param("attitudeScore") int attitudeScore,
                       @Param("createTime") Date createTime,@Param("updateTime") Date updateTime,
                       @Param("comment") String comment);
}
