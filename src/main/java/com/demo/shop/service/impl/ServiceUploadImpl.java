package com.demo.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.ServiceUpload;
import com.demo.shop.entity.add.ServiceUploadAdd;
import com.demo.shop.entity.find.MatchResult;
import com.demo.shop.mapper.ServiceTotalMapper;
import com.demo.shop.mapper.ServiceUploadMapper;
import com.demo.shop.service.ISearchService;
import com.demo.shop.service.IServiceUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUploadImpl extends ServiceImpl<ServiceUploadMapper, ServiceUpload> implements IServiceUpload {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceUploadMapper serviceUploadMapper;

    @Autowired
    private ServiceTotalMapper serviceTotalMapper;

    @Autowired
    private ISearchService searchService;

    @Override
    public ReturnData<MatchResult> getMatchResult(String words) {
        MatchResult matchResult = new MatchResult();
        String organization = "";
        String project = "";
        String standard = "";
        String equipment = "";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:9090", words, String.class);
            List<Integer> tags = JSON.parseObject(responseEntity.getBody()).getObject("result", new TypeReference<List<Integer>>() {
            });
            int index = 0;
            int end = 0;
            while (index < tags.size()) {
                switch (tags.get(index)) {
                    case 0:
                        end = index + 1;
                        while (end < tags.size() && tags.get(end) == 1) {
                            end += 1;
                        }
                        organization = organization + words.substring(index, end) + ",";
                        index = end;
                        break;
                    case 2:
                        end = index + 1;
                        while (end < tags.size() && tags.get(end) == 3) {
                            end += 1;
                        }
                        project = project + words.substring(index, end) + ",";
                        index = end;
                        break;
                    case 4:
                        end = index + 1;
                        while (end < tags.size() && tags.get(end) == 5) {
                            end += 1;
                        }
                        standard = standard + words.substring(index, end) + ",";
                        index = end;
                        break;
                    case 6:
                        end = index + 1;
                        while (end < tags.size() && tags.get(end) == 7) {
                            end += 1;
                        }
                        equipment = equipment + words.substring(index, end) + ",";
                        index = end;
                        break;
                    default:
                        index += 1;
                }
            }
            if (organization.length() > 0) {
                matchResult.setOrganization(organization.substring(0, organization.length() - 1));
            }
            if (project.length() > 0) {
                matchResult.setProject(project.substring(0, project.length() - 1));
            }
            if (standard.length() > 0) {
                matchResult.setStandard(standard.substring(0, standard.length() - 1));
            }
            if (equipment.length() > 0) {
                matchResult.setEquipment(equipment.substring(0, equipment.length() - 1));
            }
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), matchResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }

    @Override
    public ReturnData<String> serviceUpload(ServiceUploadAdd serviceUploadAdd) {
        try{
            ServiceUpload serviceUpload = new ServiceUpload();
            BeanUtils.copyProperties(serviceUploadAdd,serviceUpload);
            serviceUploadMapper.insert(serviceUpload);

            // 将新上传的服务插入到服务库中
            ServiceTotal serviceTotal = new ServiceTotal();
            BeanUtils.copyProperties(serviceUploadAdd,serviceTotal);
            serviceTotalMapper.insert(serviceTotal);

            // 为新上传的服务创建索引结构
            List<ServiceTotal> serviceTotalList=new ArrayList<>();
            serviceTotalList.add(serviceTotal);
            searchService.createIndex(serviceTotalList);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(),"服务上传成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(),"服务上传失败");
        }
    }

    @Override
    public ReturnData<List<ServiceUpload>> getServiceUploadList() {
        try{
            List<ServiceUpload> serviceUploadList = serviceUploadMapper.selectList(null);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(),serviceUploadList);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(),null);
        }
    }
}
