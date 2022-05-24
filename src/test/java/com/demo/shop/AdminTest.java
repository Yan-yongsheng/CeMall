package com.demo.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.service.AdminService;
import com.demo.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: yys
 * @Date: 2022/5/23 21:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminTest.class);
    @Autowired
    AdminService adminService;

    @Test
    public  void testAllService(){
        try {

            ReturnData serviceFind = adminService.allService(new Page<>(0, 100));
            System.out.println(serviceFind.getData());
            logger.info("[AdminTest.testAllService][run]");
        }catch (Exception e){
            //
        }

    }


}
