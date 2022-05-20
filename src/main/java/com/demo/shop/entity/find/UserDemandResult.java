package com.demo.shop.entity.find;

import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.UserDemand;
import lombok.Data;

import java.util.List;

@Data
public class UserDemandResult {
    private UserDemand userDemand;

    private List<List<ServiceTotal>> resultList;

    private int sum;

    private int groupSum;
}
