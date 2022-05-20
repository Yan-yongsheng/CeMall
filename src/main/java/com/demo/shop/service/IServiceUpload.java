package com.demo.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.ServiceUpload;
import com.demo.shop.entity.add.ServiceUploadAdd;
import com.demo.shop.entity.find.MatchResult;

import java.util.List;

public interface IServiceUpload extends IService<ServiceUpload> {
    ReturnData<MatchResult> getMatchResult(String words);
    ReturnData<String> serviceUpload(ServiceUploadAdd serviceUploadAdd);
    ReturnData<List<ServiceUpload>> getServiceUploadList();
}
