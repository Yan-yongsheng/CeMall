package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.add.OrderDemandAdd;
import com.demo.shop.entity.find.ServiceFind;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:30
 */

public interface AdminService {

    ReturnData<IPage<ServiceFind>> allService(Page<ServiceFind> page);

}