package com.demo.shop.controller;

import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.add.RateWeightAdd;
import com.demo.shop.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:29
 */
@RestController
@RequestMapping("/expert")
public class ExpertController {
    private static final Logger logger = LoggerFactory.getLogger(ExpertController.class);
    @Resource
    ExpertService expertService;

    @PostMapping("/uploadRateWeight/")
    @CrossOrigin("*")
    ReturnData uploadRateWeight(@RequestBody RateWeightAdd rateWeightAdd) {
        try {
            expertService.updateWeight(rateWeightAdd);
            logger.info("[ExpertController.uploadRateWeight][success]");
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                        StateCode.SUCCESS.getMsg(), "更新权重成功");

        }catch (Exception e){
            logger.error("[ExpertController.uploadRateWeight][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "更新权重失败");
        }

    }


}
