package com.demo.shop;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.add.ServiceUploadAdd;
import com.demo.shop.service.CompanyService;
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
public class CompanyServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceTest.class);
    @Autowired
    CompanyService companyService;
    @Test
    public  void testUpdate(){
        try {
            for(int i=0;i<100;i++){
                ServiceUploadAdd serviceUploadAdd =new ServiceUploadAdd();
                serviceUploadAdd.setDetectCompany("ceCom"+i);
                serviceUploadAdd.setDetectObject("电池");
                serviceUploadAdd.setDetectProject("性能"+i);
                serviceUploadAdd.setDetectPrice(i*i);
                serviceUploadAdd.setDetectTime(8);
                serviceUploadAdd.setDetectStandard("GB/T18287");
                companyService.uploadService(serviceUploadAdd.getDetectCompany(),serviceUploadAdd.getDetectObject(),
                        serviceUploadAdd.getDetectProject(),serviceUploadAdd.getDetectPrice(),
                        serviceUploadAdd.getDetectTime(),serviceUploadAdd.getDetectStandard());

            }

            logger.info("[CompanyServiceTest.testUpdate][run]");
        }catch (Exception e){
            //
        }

    }
    @Test
    public  void testMyService(){
        try {
            String detectCompany ="一站测测测测";
            ReturnData serviceFind = companyService.myService(detectCompany,new Page<>(0, 1000));
            System.out.println(serviceFind.getData());
            logger.info("[CompanyServiceTest.testUpdate][run]");
        }catch (Exception e){
            //
        }

    }


}
