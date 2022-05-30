package com.demo.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.shop.entity.find.ServiceFind;
import com.demo.shop.mapper.AdminMapper;
import com.demo.shop.service.EsFindService;
import com.demo.shop.service.EsFindServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.demo.shop.common.ReturnData;

import javax.annotation.Resource;
import java.util.List;
import java.util.Iterator;

/**
 * ES搜索管理Service实现类
 */
@Service
public class EsFindServiceImpl implements EsFindService{
    @Autowired
    private EsFindServiceRepository esFindServiceRepository;
    @Resource
    AdminMapper adminMapper;

    @Override
    public void importAll() {
        List<ServiceFind> serviceFinds = adminMapper.allService();
        Iterable<ServiceFind> esProductIterable = esFindServiceRepository.saveAll(serviceFinds);
        Iterator<ServiceFind> iterator = esProductIterable.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Override
    public ReturnData<IPage<ServiceFind>> search(String detectObject, String detectProject, Page<ServiceFind> page) {

        ReturnData<IPage<ServiceFind>> serviceFinds = esFindServiceRepository.findByDetectObjectAndDetectProject(detectObject, detectProject, page);
        return  serviceFinds;
    }
}
