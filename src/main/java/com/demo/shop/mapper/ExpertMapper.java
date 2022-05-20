package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.entity.OrderTotal;
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
public interface ExpertMapper extends BaseMapper<OrderTotal> {
    void updateWeight(@Param("expertId") String expertId,@Param("name") String name,@Param("phone") String phone,
                      @Param("mail") String mail, @Param("quality") String quality,
                      @Param("speed") String speed, @Param("attitude") String attitude,
                      @Param("service") String service,@Param("organization") String organization,
                      @Param("createTime") Date createTime);
}

