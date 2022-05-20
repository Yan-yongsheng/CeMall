package com.demo.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.UserDemand;
import com.demo.shop.entity.add.UserDemandAdd;
import com.demo.shop.entity.find.UserDemandResult;

import java.util.List;

public interface IUserDemand extends IService<UserDemand> {
    ReturnData<String> addUserDemand(UserDemandAdd userDemandAdd);

    ReturnData<List<UserDemand>> findUserDemandList();

    ReturnData<UserDemand> findUserDemandById(int id);

    ReturnData<UserDemandResult> getUserDemandResult(int id);

    ReturnData<UserDemandResult> getUserLimitResult(int id);

    ReturnData<String> submitComment(String id,String score,String commentContent);
}
