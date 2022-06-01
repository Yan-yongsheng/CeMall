package com.demo.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.service.AdminService;
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
    @CrossOrigin("*")
    public ReturnData allService(){
        try {
            logger.info("[AdminController.allService][run]");
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
    @CrossOrigin("*")
    public ReturnData allOrder(){
        try {
            logger.info("[AdminController.allOrder][run]");
            //检测对象 ，检测名称
            ReturnData OrderFind = adminService.allOrder(new Page<>(0, 1000));
            return OrderFind;
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }

    @GetMapping("/updateRate")
    @CrossOrigin("*")
    public ReturnData updateRate(){
        try {
            logger.info("[AdminController.updateRate][run]");
            adminService.updateRate();
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "更新评分成功");
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "更新评分失败");
        }
    }
    @GetMapping("/deleteService")
    @CrossOrigin("*")
    public ReturnData deleteService(@RequestParam("serviceId") String serviceId){
        try {
            logger.info("[AdminController.deleteService][run]");
            adminService.deleteService(serviceId);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "删除服务成功");
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "删除服务失败");
        }
    }

    @GetMapping("/deleteOrder")
    @CrossOrigin("*")
    public ReturnData deleteOrder(@RequestParam("orderNumber") String orderNumber){
        try {
            logger.info("[AdminController.deleteOrder][run]");
            adminService.deleteOrder(orderNumber);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "删除订单成功");
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "删除订单失败");
        }
    }

}
