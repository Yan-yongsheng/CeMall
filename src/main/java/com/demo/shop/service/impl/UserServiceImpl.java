package com.demo.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.entity.add.RatingUploadAdd;
import com.demo.shop.entity.find.ServiceFind;
import com.demo.shop.mapper.UserMapper;
import com.demo.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
    public void makeOrder(OrderDemandAdd orderDemandAdd){
        //生成订单
        try {
            String orderNumber = getOrderNumber(orderDemandAdd.getUserName());
            userMapper.makeOrder(orderNumber,orderDemandAdd.getDetectCompany(),orderDemandAdd.getDetectObject(),
                    orderDemandAdd.getDetectProject(),orderDemandAdd.getDetectPrice(),orderDemandAdd.getDetectTime(),
                    orderDemandAdd.getDetectStandard(),orderDemandAdd.getUserName(),new Date(),new Date());
        }catch (Exception e){
            logger.error("[UserServiceImpl.makeOrder][error]",e);
        }

//        return orderNumber;
    }
    @Override
    public String completeOrder(String orderId,String serviceId,String userId){
        userMapper.completeOrder(orderId,serviceId,userId,new Date());
        //要和提交评价一起吗
        return "";
    }
    @Override
    public ReturnData<IPage<ServiceFind>> allService(String detectObject,String detectProject,Page<ServiceFind> page){
        List<ServiceFind> serviceFinds = userMapper.allService(detectObject,detectProject);
        page.setRecords(serviceFinds);
        return new ReturnData<>(StateCode.SUCCESS.getCode(),
                StateCode.SUCCESS.getMsg(), page);
    }

    @Override
    public void submitComment(RatingUploadAdd ratingUploadAdd){
        try {
            userMapper.submitComment(ratingUploadAdd.getDetectCompany(), ratingUploadAdd.getDetectObject(), ratingUploadAdd.getDetectProject(),
                    ratingUploadAdd.getUserName(), ratingUploadAdd.getQualityScore(), ratingUploadAdd.getSpeedScore(), ratingUploadAdd.getAttitudeScore(),
                    new Date(),new Date(), ratingUploadAdd.getComment());
        }catch (Exception e){
            logger.error("[UserServiceImpl.submitComment][error]",e);
        }

    }


    private static String getOrderNumber(String userName){
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //3位随机数
        int randomNumeric = (int)(Math.random()*900)+100;
        String orderNum = "order"+localDate + randomNumeric ;
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