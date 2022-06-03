package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.find.ServiceFind;
import org.springframework.data.domain.Page;


/**
 * ES管理Service
 */
public interface EsFindService {
    /**
     * 从数据库中导入所有项目到ES
    */
    void importAll();

    /**
     * 根据对象项目搜索
     */
//    Page<ServiceFind> search(String detectObject, String detectProject, Page<ServiceFind> page);

    Page<ServiceFind> search(String detectObject, String detectProject,Integer pageNum, Integer pageSize);

}
