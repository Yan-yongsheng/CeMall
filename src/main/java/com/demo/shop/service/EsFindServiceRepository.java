package com.demo.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.find.ServiceFind;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES操作类
 */
public interface EsFindServiceRepository extends ElasticsearchRepository<ServiceFind,Long>{

    org.springframework.data.domain.Page<ServiceFind> findByDetectObjectAndDetectProject(String detectObject, String detectProject, Pageable page);
}
