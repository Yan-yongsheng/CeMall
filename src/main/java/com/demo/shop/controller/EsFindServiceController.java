package com.demo.shop.controller;

import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.service.EsFindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


@RestController
@RequestMapping("/EsFindService")
public class EsFindServiceController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private EsFindService esFindService;

    @PostMapping("/importAll")
    ReturnData importAll() {
        try {
            esFindService.importAll();
            logger.info("[EsFindServiceController.importAll][success]");
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "导入ES成功");

        }catch (Exception e){
            logger.error("[EsFindServiceController.importAll][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "导入ES失败");
        }

    }

    @GetMapping("/findService")
    public ReturnData findService(String requirement){
        try {
            //下一步是多个怎么区分，以及返回格式问题，组合办法
            String[] requirements = requirement.split(" ");
            String detectObject = requirements[0],detectProject = requirements[1];
            //检测对象 ，检测名称  后端分页还是前端分页？
            ReturnData serviceFind = esFindService.search(detectObject,detectProject,new Page<>(0, 1000));
            return serviceFind;
        }catch (Exception e){
            logger.error("[EsFindServiceController.findService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }

}
