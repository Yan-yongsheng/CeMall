package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.find.ServiceFind;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES操作类
 */
public interface EsFindServiceRepository extends ElasticsearchRepository<ServiceFind,Long>{

    ReturnData<IPage<ServiceFind>> findByDetectObjectAndDetectProject(String detectObject, String detectProject, Page<ServiceFind> page);
}
