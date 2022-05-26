package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.Service;
import com.demo.shop.entity.ServiceUpload;
import com.demo.shop.entity.find.OrderFind;
import com.demo.shop.entity.find.ServiceFind;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/24 21:27
 */
@Repository
public interface CompanyServiceMapper extends BaseMapper<Service> {
    //String serviceId, String detectCompany, String detectObject, String detectProject,
    //                       double detectPrice, int detectTime, Date createTime, Date updateTime
    void updateService(@Param("serviceId") String serviceId, @Param("detectCompany") String detectCompany,
                       @Param("detectObject") String detectObject,
                       @Param("detectProject") String detectProject,
                       @Param("detectPrice") Double detectPrice, @Param("detectTime") Integer detectTime,
                       @Param("detectStandard") String detectStandard,
                       @Param("createTime") Date createTime,@Param("updateTime") Date updateTime);

    List<ServiceFind> myService(@Param("detectCompany") String detectCompany);
    List<OrderFind> myOrder(@Param("detectCompany") String detectCompany);
}
