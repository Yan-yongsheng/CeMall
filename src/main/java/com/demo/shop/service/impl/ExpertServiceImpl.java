package com.demo.shop.service.impl;

import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.add.RateWeightAdd;
import com.demo.shop.mapper.ExpertMapper;
import com.demo.shop.mapper.UserMapper;
import com.demo.shop.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:30
 */
@Service
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    ExpertMapper expertMapper;
    @Override
    public void updateWeight(RateWeightAdd rateWeightAdd){
        expertMapper.updateWeight(rateWeightAdd.getAccount(),
                rateWeightAdd.getQuality(), rateWeightAdd.getSpeed(), rateWeightAdd.getAttitude(), new Date());
    }
}