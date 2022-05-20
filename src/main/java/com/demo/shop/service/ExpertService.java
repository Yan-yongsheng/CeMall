package com.demo.shop.service;

import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.add.RateWeightAdd;
import com.demo.shop.entity.find.SearchResult;
import org.apache.lucene.search.TopDocs;

import java.util.List;

public interface ExpertService {
    void updateWeight(RateWeightAdd rateWeightAdd,String expertId);

}
