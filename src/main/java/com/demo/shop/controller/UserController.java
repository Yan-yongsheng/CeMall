package com.demo.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.User;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.entity.add.RatingUploadAdd;
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
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;


    @PostMapping("/register")
    @CrossOrigin("*")
    ReturnData register(@RequestBody User user) {
        try {
            userService.register(user.getAccount(), user.getPassword(), user.getCategory());
            logger.info("注册 账号：{} 密码：{} 类别 :{}",user.getAccount(),user.getPassword(), user.getCategory());
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                        StateCode.SUCCESS.getMsg(), "注册成功");

        }catch (Exception e){
            logger.error("[register] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "注册失败");
        }

    }

    @PostMapping("/login")
    @CrossOrigin("*")
    ReturnData login(@RequestBody User user) {
        try {
            int res = userService.login(user.getAccount(), user.getPassword(), user.getCategory());
            logger.info("登录 账号：{} 密码：{} 类别： {} 结果:{}",user.getAccount(),user.getPassword(), user.getCategory(),res);
            //防止多次注册 只要有就登录
            if(res>=1) {
                //返回前端身份类别，方便根据数据跳转
                return new ReturnData<>(StateCode.SUCCESS.getCode(),
                        StateCode.SUCCESS.getMsg(), user.getCategory());
            } else {
                return new ReturnData<>(StateCode.FAIL.getCode(),
                        StateCode.FAIL.getMsg(), "登录失败");
            }
        }catch (Exception e){
            logger.error("[login] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "登录失败");
        }

    }
    @PostMapping("/makeOrder")
    @CrossOrigin("*")
    ReturnData makeOrder(@RequestBody OrderDemandAdd orderDemandAdd) {
        try {
            logger.info("[UserController.makeOrder][run]");
            userService.makeOrder(orderDemandAdd);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                        StateCode.SUCCESS.getMsg(), "新增订单成功");

        }catch (Exception e){
            logger.error("[login] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "交易失败");
        }

    }
    @GetMapping("/findService")
    @CrossOrigin("*")
    public ReturnData findService( String requirement){
        try {
            logger.info("[UserController.findService][run]");
            //下一步是多个怎么区分，以及返回格式问题，组合办法
            //分割彻底，多个空格也一样，以及两边
            String[] requirements = requirement.trim().split("\\s+");
            String detectObject = requirements[0],detectProject = requirements[1];
            //检测对象 ，检测名称  后端分页还是前端分页？
            ReturnData serviceFind = userService.allService(detectObject,detectProject,new Page<>(0, 1000));
            return serviceFind;
        }catch (Exception e){
            logger.error("[CompanyController.myService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }
    @GetMapping("/myOrder")
    @CrossOrigin("*")
    public ReturnData allOrder(String userName){
        try {
            logger.info("[UserController.allOrder][run]");
            //检测对象 ，检测名称
            ReturnData OrderFind = userService.myOrder(userName,new Page<>(0, 1000));
            return OrderFind;
        }catch (Exception e){
            logger.error("[CompanyController.allOrder][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }

    @GetMapping("/allService")
    @CrossOrigin("*")
    public ReturnData allService(@RequestParam("detectObject") String detectObject,@RequestParam("detectProject") String detectProject){
        try {
            logger.info("[UserController.allService][run]");
            //检测对象 ，检测名称
            ReturnData serviceFind = userService.allService(detectObject,detectProject,new Page<>(0, 1000));
            return serviceFind;
        }catch (Exception e){
            logger.error("[CompanyController.myService][error]",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "查询服务失败");
        }
    }


    //提交评分
    @PostMapping("/comment")
    @CrossOrigin("*")
    ReturnData submitComment(@RequestBody RatingUploadAdd ratingUploadAdd) {
        try {
            logger.info("[UserController.submitComment][run]");
            userService.submitComment(ratingUploadAdd);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "提交评价成功");

        }catch (Exception e){
            logger.error("[submitComment] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "提交评价失败");
        }

    }
}
