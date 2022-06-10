package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.Service;
import com.demo.shop.entity.ServiceRate;
import com.demo.shop.entity.find.OrderFind;
import com.demo.shop.entity.find.RateWeightFind;
import com.demo.shop.entity.find.ServiceFind;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:31
 */
@Mapper
public interface AdminMapper extends BaseMapper<Service> {
    List<ServiceFind> allService();
    List<OrderFind> allOrder();
    void deleteService(@Param("serviceId") String serviceId);
    void deleteOrder(@Param("orderNumber") String orderNumber);
    RateWeightFind findWeight();
    List<ServiceRate> getOrderRate();
    void updateServiceRate(@Param("serviceId") String serviceId,@Param("detectScore") double detectScore);

}
