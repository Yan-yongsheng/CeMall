package com.demo.shop.entity.find;

import com.demo.shop.entity.ServiceTotal;
import lombok.Data;

import java.util.List;

@Data
public class SearchResult {
    public long totalService;

    public List<ServiceTotal> serviceResult;
}
