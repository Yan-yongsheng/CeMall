package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.find.CategoryContent;
import com.demo.shop.entity.find.CategoryReturn;


public interface IServiceTotal extends IService<ServiceTotal> {
    ReturnData<CategoryReturn> getCategories();

    ReturnData<IPage<CategoryContent>> getCategoryContent(String categoryOne, String categoryTwo, Page<CategoryContent> page);
}
