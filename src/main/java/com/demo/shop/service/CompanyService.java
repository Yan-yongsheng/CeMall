package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.find.CategoryContent;
import com.demo.shop.entity.find.ServiceFind;

import java.util.Date;

/**
 * @Author: yys
 * @Date: 2022/5/24 21:21
 */
public interface CompanyService {
    void uploadService( String detectCompany, String detectObject, String detectProject,
                       double detectPrice, int detectTime,String detectStandard);

    ReturnData<IPage<ServiceFind>> myService(String detectCompany, Page<ServiceFind> page);
}
