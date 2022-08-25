package com.demo.shop.controller;

import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.find.ServiceFind;
import com.demo.shop.service.EsFindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/EsFindService")
public class EsFindServiceController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private EsFindService esFindService;

    @PostMapping("/importAll")
    @CrossOrigin("*")
    public ReturnData importAll() {
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
    @CrossOrigin("*")
    public ReturnData findService( String requirement){
        try {
            logger.info("[EsFindServiceController.findService][run]");

            String[] requirements = requirement.trim().split("\\s+");
            if(requirements.length<2){
                return new ReturnData<>(StateCode.FAIL.getCode(),
                        StateCode.FAIL.getMsg(), "查询失败，请输入对象与项目，使用空格分隔");
            }
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<ServiceFind> page =new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
            List<ServiceFind> res = new ArrayList<>();
            String detectObject = requirements[0];
            for(int i=1;i<requirements.length;i++){
                Page<ServiceFind> serviceFindPage = esFindService.search(detectObject,requirements[i],0,10);
//                List<ServiceFind> curProjects = userService.findService(detectObject,requirements[i]);
                res.addAll(serviceFindPage.toList());
            }
            Collections.sort(res, new Comparator<ServiceFind>() {
                @Override
                public int compare(ServiceFind o1, ServiceFind o2) {
                    return (int) (o1.getDetectScore()== o2.getDetectScore()?o1.getDetectPrice()-o2.getDetectPrice():
                                                o1.getDetectScore()-o2.getDetectScore());
                }
            });
            page.setRecords(res);
            page.setTotal(res.size());
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), page);

        }catch (Exception e){
            logger.error("[EsFindServiceController.findService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }

//    @GetMapping("/findService")
//    public ReturnData findService(String requirement){
//        try {
//            //多个项目进行查询并且组合返回
//            //error No property detectObject found for type ReturnData!
//            String[] requirements = requirement.trim().split("\\s+");
//            String detectObject = requirements[0],detectProject = requirements[1];
//            //检测对象 ，检测名称  后端分页还是前端分页？
//
//            Page<ServiceFind> serviceFindPage = esFindService.search(detectObject,detectProject,0,100);
//
//            return new ReturnData<>(StateCode.SUCCESS.getCode(),
//                    StateCode.SUCCESS.getMsg(), serviceFindPage);
//        }catch (Exception e){
//            logger.error("[EsFindServiceController.findService][error]",e);
//            return new ReturnData<>(StateCode.FAIL.getCode(),
//                    StateCode.FAIL.getMsg(), "查询服务失败");
//        }
//    }

}
