package com.demo.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.find.CategoryContent;
import com.demo.shop.entity.find.CategoryReturn;
import com.demo.shop.mapper.ServiceTotalMapper;
import com.demo.shop.service.IServiceTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTotalImpl extends ServiceImpl<ServiceTotalMapper, ServiceTotal> implements IServiceTotal {
    @Autowired
    private ServiceTotalMapper serviceTotalMapper;

    @Override
    public ReturnData<CategoryReturn> getCategories() {
        try {
            CategoryReturn categoryReturn = new CategoryReturn();
            categoryReturn.setCategoryList(serviceTotalMapper.getCategories());
            categoryReturn.setTotalService(serviceTotalMapper.selectCount(null));
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), categoryReturn);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.SUCCESS.getMsg(), null);
        }
    }

    @Override
    public ReturnData<IPage<CategoryContent>> getCategoryContent(String categoryOne, String categoryTwo, Page<CategoryContent> page) {
        try {
            List<CategoryContent> categoryContents = serviceTotalMapper.getCategoryContent(categoryOne, categoryTwo, page);
            page.setRecords(categoryContents);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), page);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }
}
