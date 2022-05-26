package com.demo.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.service.AdminService;
import com.demo.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:29
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Resource
    AdminService adminService;


    @GetMapping("/allService")
    public ReturnData allService(){
        try {
            //检测对象 ，检测名称
            ReturnData serviceFind = adminService.allService(new Page<>(0, 1000));
            return serviceFind;
        }catch (Exception e){
            logger.error("[CompanyController.myService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }
    @GetMapping("/allOrder")
    public ReturnData allOrder(){
        try {
            //检测对象 ，检测名称
            ReturnData OrderFind = adminService.allOrder(new Page<>(0, 1000));
            return OrderFind;
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }

}
