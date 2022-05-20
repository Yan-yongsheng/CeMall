package com.demo.shop.service;

import com.demo.shop.common.ReturnData;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.find.SearchResult;
import org.apache.lucene.search.TopDocs;

import java.util.List;

public interface ISearchService {
    ReturnData<SearchResult> searchForService(String keyWords, int page, int pageSize);
    void createIndex(List<ServiceTotal> serviceTotalList);
    List<String> getIkResolveWords(String text);
    TopDocs getTopDocs(String text, int limits) throws Exception;
    ServiceTotal getServiceTotalFromDocId(int docId) throws Exception;
}
