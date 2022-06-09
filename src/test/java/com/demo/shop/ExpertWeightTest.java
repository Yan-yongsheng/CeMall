package com.demo.shop;

import com.demo.shop.entity.add.RateWeightAdd;
import com.demo.shop.service.ExpertService;
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
public class ExpertWeightTest {
    private static final Logger logger = LoggerFactory.getLogger(ExpertWeightTest.class);
    @Autowired
    ExpertService expertService;
    @Test
    public  void testExpert(){
        try {
            for(int i=0;i<10;i++){
                RateWeightAdd rateWeightAdd  = new RateWeightAdd();
                rateWeightAdd.setAccount("expert"+i);
                rateWeightAdd.setQuality(20+i);
                rateWeightAdd.setSpeed(50-2*i);
                rateWeightAdd.setAttitude(30+i);
                expertService.updateWeight(rateWeightAdd);
            }

        }catch (Exception e){
            //
        }

    }


}
