package com.demo.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.controller.UserController;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.entity.add.RatingUploadAdd;
import com.demo.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/23 21:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLoginTest {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginTest.class);
    @Autowired
    UserService userService;
    @Test
    public  void testLogin(){
        try {
            for(int i=0;i<10;i++){
                String account  = "user"+i,password = "user"+i,category = "user";
                userService.register(account,password,category);
            }
            for(int i=0;i<10;i++){
                String account  = "company"+i,password = "company"+i,category = "company";
                userService.register(account,password,category);
            }
            for(int i=0;i<10;i++){
                String account  = "expert"+i,password = "expert"+i,category = "expert";
                userService.register(account,password,category);
            }
            String account  = "admin",password = "admin",category = "admin";
            userService.register(account,password,category);

            Thread.sleep(2000);
//            int res = userService.login(account,password,category);
//            logger.info("登录 账号：{} 密码：{} 类别： {} 结果:{}",account,password,category,res);
        }catch (Exception e){
            //
        }

    }
    @Test
    public  void testAllService(){
        try {
            String detectObject ="电池",detectProject ="防火性能";
            ReturnData serviceFind = userService.allService(detectObject,detectProject, new Page<>(0, 100));
            System.out.println(serviceFind.getData());
            logger.info("[UserLoginTest.testAllService][run]");
        }catch (Exception e){
            //
        }

    }

    @Test
    public  void testMakeOrder(){
        try {
//            for(int i=0;i<5;i++){
//                OrderDemandAdd orderDemandAdd =new OrderDemandAdd();
//                orderDemandAdd.setServiceId("service20220609222537255587");
//                orderDemandAdd.setDetectCompany("company0");
//                orderDemandAdd.setDetectObject("新能源");
//                orderDemandAdd.setDetectProject("锂电池");
//                orderDemandAdd.setDetectPrice(353);
//                orderDemandAdd.setDetectTime(12);
//                orderDemandAdd.setDetectStandard("GB/T1000");
//                orderDemandAdd.setUserName("user"+i);
//                userService.makeOrder(orderDemandAdd);
//            }
            for(int i=0;i<10;i++){
                OrderDemandAdd orderDemandAdd =new OrderDemandAdd();
                orderDemandAdd.setServiceId("service20220609222537684838");
                orderDemandAdd.setDetectCompany("company2");
                orderDemandAdd.setDetectObject("新能源");
                orderDemandAdd.setDetectProject("轮胎");
                orderDemandAdd.setDetectPrice(264);
                orderDemandAdd.setDetectTime(7);
                orderDemandAdd.setDetectStandard("GB/T1002");
                orderDemandAdd.setUserName("user"+i);
                userService.makeOrder(orderDemandAdd);
            }

            logger.info("[UserLoginTest.testMakeOrder][run]");
        }catch (Exception e){
            //
        }

    }

    @Test
    public  void testComment(){
        try {
            RatingUploadAdd ratingUploadAdd =new RatingUploadAdd();
            ratingUploadAdd.setDetectCompany("一站测测测");
            ratingUploadAdd.setDetectObject("电池");
            ratingUploadAdd.setDetectProject("防火性能");
            ratingUploadAdd.setUserName("张三");
            ratingUploadAdd.setQualityScore(5);
            ratingUploadAdd.setSpeedScore(4);
            ratingUploadAdd.setAttitudeScore(3);
            ratingUploadAdd.setComment("态度不好");
            userService.submitComment(ratingUploadAdd);
            logger.info("[UserLoginTest.testComment][run]");
        }catch (Exception e){
            //
        }

    }


}
