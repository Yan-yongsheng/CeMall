package com.demo.shop.service;

import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:30
 */

public interface UserService {

    void register(String account, String password, String category);

    int login(String account, String password, String category) ;

//    String getOrderId(Long userId);

    String makeOrder(OrderDemandAdd orderDemandAdd);
    String completeOrder(String orderId,String serviceId,String userId);

    void submitComment(String orderId,String serviceIdList,String userId,
                       String qualityScore,String speedScore,String attitudeScore,String comment);
}