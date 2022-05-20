package com.demo.shop.entity.find;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    public String categoryOne;

    public List<SubCategory> subCategory;
}
