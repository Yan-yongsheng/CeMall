package com.demo.shop.service.impl;

import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.mapper.UserMapper;
import com.demo.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:30
 */
@Service
public class UserServiceImpl implements UserService {
    //还得加 查看自己的历史订单（包括还没完成的） 通过这个可以获得orderId号码和serviceId
    //
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    UserMapper userMapper;
    @Override
    public void register(String account, String password, String category) {
        String userId = getUserId();
        userMapper.register(userId,account, password,category);
    }
    @Override
    public int login(String account, String password, String category) {
        return userMapper.login(account, password,category);
    }
    @Override
    public String makeOrder(OrderDemandAdd orderDemandAdd){

        String serviceId = orderDemandAdd.getServiceId();
        String userId = orderDemandAdd.getUserId();
        //生成订单号
        String orderId = getOrderId(userId);
        String price  = orderDemandAdd.getPrice();
        String cycle = orderDemandAdd.getCycle();
        //生成订单
        userMapper.makeOrder(orderId,serviceId,userId,price,cycle,new Date());
        return orderId;
    }
    @Override
    public String completeOrder(String orderId,String serviceId,String userId){
        userMapper.completeOrder(orderId,serviceId,userId,new Date());
        //要和提交评价一起吗
        return "";
    }

    @Override
    public void submitComment(String orderId,String serviceId,String userId,
                              String qualityScore,String speedScore,String attitudeScore,String comment){
        userMapper.submitComment(orderId,serviceId,userId,qualityScore,speedScore,attitudeScore,comment);
    }


    private static String getOrderId(String userId){
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //3位随机数
        int randomNumeric = (int)(Math.random()*900)+100;
        //5位用户id
        int subStrLength = 5;
        String sUserId = userId.toString();
        int length = sUserId.length();
        String str;
        if (length >= subStrLength) {
            str = sUserId.substring(length - subStrLength, length);
        } else {
            str = String.format("%0" + subStrLength + "d", userId);
        }
        String orderNum = localDate + randomNumeric + str;
        logger.info("订单号:{}", orderNum);
        return orderNum;

    }

    private static String getUserId(){
        long timeStamp = System.currentTimeMillis();
        //3位随机数
        int randomNumeric = (int)(Math.random()*900)+100;

        String orderNum = timeStamp +""+ randomNumeric;
        logger.info("用户编号:{}", orderNum);
        return orderNum;

    }
}