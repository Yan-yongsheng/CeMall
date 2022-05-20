package com.demo.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.find.Category;
import com.demo.shop.entity.find.CategoryContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTotalMapper extends BaseMapper<ServiceTotal> {
    List<Category> getCategories();

    List<CategoryContent> getCategoryContent(@Param("categoryOne") String categoryOne,
                                              @Param("categoryTwo") String categoryTwo,
                                              Page<CategoryContent> page);
}
