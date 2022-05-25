package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.RateWeight;
import com.demo.shop.entity.ServiceRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:19
 */
@Repository
public interface ExpertMapper extends BaseMapper<RateWeight> {
    void updateWeight(@Param("account") String account, @Param("quality") Integer quality,
                      @Param("speed") Integer speed, @Param("attitude") Integer attitude,
                      @Param("createTime") Date createTime);
}

