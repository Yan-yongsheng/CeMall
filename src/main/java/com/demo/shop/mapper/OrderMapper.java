package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.ServiceRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:19
 */
@Repository
public interface OrderMapper extends BaseMapper<OrderTotal> {
    //从订单(带有每笔交易评分）表中获取近3个月各服务的三项评分
    List<ServiceRate> getOrderRate();

}

