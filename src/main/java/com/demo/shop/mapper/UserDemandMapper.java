package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shop.entity.UserDemand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDemandMapper extends BaseMapper<UserDemand> {
    void submitComment(@Param("id") int id, @Param("qualityScore") int qualityScore,
                       @Param("speedScore") int speedScore, @Param("attitudeScore") int attitudeScore);

}
