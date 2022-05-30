package com.demo.shop.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.entity.add.ServiceUploadAdd;
import com.demo.shop.entity.find.ServiceFind;
import com.demo.shop.service.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Resource
    CompanyService companyService ;


    @PostMapping("/uploadService")
    @CrossOrigin("*")
    public ReturnData uploadService(@RequestBody ServiceUploadAdd serviceUploadAdd){
        try {
            logger.info("[CompanyController.uploadService][run]");
            companyService.uploadService(serviceUploadAdd.getDetectCompany(),serviceUploadAdd.getDetectObject(),
                    serviceUploadAdd.getDetectProject(),serviceUploadAdd.getDetectPrice(),
                    serviceUploadAdd.getDetectTime(),serviceUploadAdd.getDetectStandard());
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "发布服务成功");
        }catch (Exception e){
            logger.error("[CompanyController.uploadService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "发布服务失败");
        }
    }

    @GetMapping("/myService")
    @CrossOrigin("*")
    public ReturnData myService(@RequestParam("detectCompany") String detectCompany){
        try {
            logger.info("[CompanyController.myService][run]");
//            Page<ServiceFind> page =new Page();
//            page.setSize(1000);
            ReturnData serviceFind = companyService.myService(detectCompany,new Page<>(0, 1000));
            return serviceFind;
        }catch (Exception e){
            logger.error("[CompanyController.myService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }

    @GetMapping("/myOrder")
    @CrossOrigin("*")
    public ReturnData myOrder(@RequestParam("detectCompany")String detectCompany){
        try {
            logger.info("[CompanyController.myOrder][run]");

            //检测对象 ，检测名称
            ReturnData OrderFind = companyService.myOrder(detectCompany,new Page<>(0, 1000));
            return OrderFind;
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }
}
