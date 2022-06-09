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

import java.util.Random;

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
            Random random =new Random();
            for(int i=0;i<10;i++){
                ServiceUploadAdd serviceUploadAdd =new ServiceUploadAdd();
                serviceUploadAdd.setDetectCompany("company"+i);
                serviceUploadAdd.setDetectObject("新能源");
                serviceUploadAdd.setDetectProject("锂电池");
                serviceUploadAdd.setDetectPrice(random.nextInt(1000)+200);
                serviceUploadAdd.setDetectTime(random.nextInt(10)+3);
                serviceUploadAdd.setDetectStandard("GB/T100"+i);
                companyService.uploadService(serviceUploadAdd.getDetectCompany(),serviceUploadAdd.getDetectObject(),
                        serviceUploadAdd.getDetectProject(),serviceUploadAdd.getDetectPrice(),
                        serviceUploadAdd.getDetectTime(),serviceUploadAdd.getDetectStandard());

            }
            for(int i=0;i<10;i++){
                ServiceUploadAdd serviceUploadAdd =new ServiceUploadAdd();
                serviceUploadAdd.setDetectCompany("company"+i);
                serviceUploadAdd.setDetectObject("新能源");
                serviceUploadAdd.setDetectProject("轮胎");
                serviceUploadAdd.setDetectPrice(random.nextInt(1000)+200);
                serviceUploadAdd.setDetectTime(random.nextInt(10)+3);
                serviceUploadAdd.setDetectStandard("GB/T100"+i);
                companyService.uploadService(serviceUploadAdd.getDetectCompany(),serviceUploadAdd.getDetectObject(),
                        serviceUploadAdd.getDetectProject(),serviceUploadAdd.getDetectPrice(),
                        serviceUploadAdd.getDetectTime(),serviceUploadAdd.getDetectStandard());

            }
            for(int i=0;i<10;i++){
                ServiceUploadAdd serviceUploadAdd =new ServiceUploadAdd();
                serviceUploadAdd.setDetectCompany("company"+i);
                serviceUploadAdd.setDetectObject("新能源");
                serviceUploadAdd.setDetectProject("发动机");
                serviceUploadAdd.setDetectPrice(random.nextInt(1000)+200);
                serviceUploadAdd.setDetectTime(random.nextInt(10)+3);
                serviceUploadAdd.setDetectStandard("GB/T100"+i);
                companyService.uploadService(serviceUploadAdd.getDetectCompany(),serviceUploadAdd.getDetectObject(),
                        serviceUploadAdd.getDetectProject(),serviceUploadAdd.getDetectPrice(),
                        serviceUploadAdd.getDetectTime(),serviceUploadAdd.getDetectStandard());

            }
            for(int i=0;i<5;i++){
                ServiceUploadAdd serviceUploadAdd =new ServiceUploadAdd();
                serviceUploadAdd.setDetectCompany("company"+i);
                serviceUploadAdd.setDetectObject("新能源");
                serviceUploadAdd.setDetectProject("机动装置");
                serviceUploadAdd.setDetectPrice(random.nextInt(1000)+200);
                serviceUploadAdd.setDetectTime(random.nextInt(10)+3);
                serviceUploadAdd.setDetectStandard("GB/T100"+i);
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
