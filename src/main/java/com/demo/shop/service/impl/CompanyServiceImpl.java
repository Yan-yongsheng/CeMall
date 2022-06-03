package com.demo.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.controller.CompanyController;
import com.demo.shop.entity.find.CategoryContent;
import com.demo.shop.entity.find.OrderFind;
import com.demo.shop.entity.find.ServiceFind;
import com.demo.shop.mapper.CompanyServiceMapper;
import com.demo.shop.mapper.ExpertMapper;
import com.demo.shop.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/24 21:24
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    @Autowired
    CompanyServiceMapper companyServiceMapper;
    @Override
    public void uploadService(String detectCompany, String detectObject, String detectProject,
                              double detectPrice, int detectTime,String detectStandard){
        String serviceId = getServiceId(detectCompany);
        companyServiceMapper.updateService(serviceId,detectCompany,detectObject,detectProject,detectPrice,
                detectTime,detectStandard,new Date(),new Date());
    }

    @Override
    public ReturnData<IPage<ServiceFind>> myService(String detectCompany, Page<ServiceFind> page){
        List<ServiceFind> serviceFinds = companyServiceMapper.myService(detectCompany);
        page.setRecords(serviceFinds);
        page.setTotal(serviceFinds.size());
        return new ReturnData<>(StateCode.SUCCESS.getCode(),
                StateCode.SUCCESS.getMsg(), page);
    }

    @Override
    public ReturnData<IPage<OrderFind>> myOrder(String detect_company,Page<OrderFind> page){
        List<OrderFind> orderFinds = companyServiceMapper.myOrder(detect_company);
        page.setRecords(orderFinds);
        page.setTotal(orderFinds.size());
        return new ReturnData<>(StateCode.SUCCESS.getCode(),
                StateCode.SUCCESS.getMsg(), page);

    }



    private static String getServiceId(String detectCompany){
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //3位随机数
        int randomNumeric = (int)(Math.random()*900)+100;

        String orderNum = "service"+localDate + randomNumeric ;
        logger.info("服务号:{}", orderNum);
        return orderNum;

    }
}
