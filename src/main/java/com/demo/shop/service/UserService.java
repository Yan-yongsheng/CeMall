package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.entity.add.RatingUploadAdd;
import com.demo.shop.entity.add.UpdateRateAdd;
import com.demo.shop.entity.find.OrderFind;
import com.demo.shop.entity.find.ServiceFind;
import com.demo.shop.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:30
 */

public interface UserService {

    void register(String account, String password, String category);

    int login(String account, String password, String category) ;

//    String getOrderId(Long userId);

    void makeOrder(OrderDemandAdd orderDemandAdd);
    String completeOrder(String orderId,String serviceId,String userId);

    ReturnData<IPage<ServiceFind>> allService(String detectObject,String detectProject,Page<ServiceFind> page);

    List<ServiceFind> findService(String detectObject,String detectProject);

    ReturnData<IPage<OrderFind>> myOrder(String userName,Page<OrderFind> page);

    void submitComment(RatingUploadAdd ratingUploadAdd);

    void updateRate(UpdateRateAdd updateRateAdd);
}