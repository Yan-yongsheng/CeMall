package com.demo.shop.service.impl;

import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.find.SearchResult;
import com.demo.shop.index.LuceneIndex;
import com.demo.shop.mapper.ServiceTotalMapper;
import com.demo.shop.service.ISearchService;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Order(2)
public class SearchServiceImpl implements ISearchService, ApplicationRunner {
    private IKAnalyzer analyzer;
    private FSDirectory directory;
    private DirectoryReader reader;
    private IndexSearcher indexSearcher;
    private String[] fields = {"categoryOne", "categoryTwo", "categoryThree", "serviceTitle", "detectionItem", "institutionTitle", "serviceDetail"};
    private Highlighter highlighter;
    private List<Integer> tmpServiceId=new ArrayList<>();

    @Autowired
    private LuceneIndex luceneIndex;

    @Autowired
    private ServiceTotalMapper serviceTotalMapper;


    public SearchServiceImpl() throws IOException {

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        this.analyzer = new IKAnalyzer(true);
//        this.directory = FSDirectory.open(Paths.get("./dir"));
//        this.reader = DirectoryReader.open(this.directory);
//        this.indexSearcher = new IndexSearcher(this.reader);
    }

    @Override
    public ReturnData<SearchResult> searchForService(String keyWords, int page, int pageSize) {
        try {
            int start = (page - 1) * pageSize;
            int end = page * pageSize;
            //设置影响排序的权重, 这里设置域的权重
//            Map<String, Float> boots = new HashMap<>();
//            boots.put("categoryThree", 20000000000f);
//            boots.put("serviceTitle", 10000000000f);
//            MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, analyzer, boots);
//            multiFieldQueryParser.setDefaultOperator(QueryParser.Operator.AND);

            TopDocs topDocs = getTopDocs(keyWords, end);
            SearchResult searchResult = new SearchResult();
            searchResult.setTotalService(topDocs.totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            List<ServiceTotal> serviceTotalList = new ArrayList<>();
            for (int i = start; i < end && i < scoreDocs.length; i++) {
                ServiceTotal serviceTotal = getServiceTotalFromDocId(scoreDocs[i].doc);
                tmpServiceId.add(serviceTotal.getId());
                serviceTotalList.add(serviceTotal);
            }
            System.out.println(tmpServiceId);
            searchResult.setServiceResult(serviceTotalList);

            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), searchResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }

    @Override
    public void createIndex(List<ServiceTotal> serviceTotalList) {
        luceneIndex.createIndex(serviceTotalList);
    }

    @Override
    public List<String> getIkResolveWords(String text) {
        try {
            //创建分词对象
            StringReader reader = new StringReader(text);
            //分词
            TokenStream ts = analyzer.tokenStream("", reader);
            CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
            ts.reset();
            //遍历分词数据
            List<String> words = new ArrayList<>();
            while (ts.incrementToken()) {
                words.add(term.toString());
            }
            ts.close();
            reader.close();
            return words;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TopDocs getTopDocs(String text, int limits) throws Exception {
        directory = FSDirectory.open(Paths.get("./dir"));
        reader = DirectoryReader.open(directory);
        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
        for (String field : fields) {
            QueryParser parser = new QueryParser(field, analyzer);
            parser.setDefaultOperator(QueryParser.Operator.AND);
            Query query = parser.parse(text);
            queryBuilder.add(query, BooleanClause.Occur.SHOULD);
        }
        this.highlighter = new Highlighter(new SimpleHTMLFormatter("<span style='color: red;font-weight: bolder;'>", "</span>"),
                new QueryScorer(queryBuilder.build()));
        highlighter.setTextFragmenter(new SimpleFragmenter(10000));
        indexSearcher = new IndexSearcher(reader);
        TopDocs search = indexSearcher.search(queryBuilder.build(), limits);
//        reader.close();
//        directory.close();
        return search;
    }

    @Override
    public ServiceTotal getServiceTotalFromDocId(int docId) throws Exception {
//        highlighter.setMaxDocCharsToAnalyze(10000);
        Document document = indexSearcher.doc(docId);
        ServiceTotal serviceTotal = new ServiceTotal();
//                serviceTotal.setCategoryOne(document.get("categoryOne"));
//                serviceTotal.setCategoryTwo(document.get("categoryTwo"));
//                serviceTotal.setCategoryThree(document.get("categoryThree"));
//                serviceTotal.setInstitutionTitle(document.get("institutionTitle"));
//                serviceTotal.setServiceTitle(document.get("serviceTitle"));
//                serviceTotal.setServiceUrl(document.get("serviceUrl"));
//                serviceTotal.setDetectionItem(document.get("detectionItem"));
//                serviceTotal.setServiceDetail(document.get("serviceDetail"));

        serviceTotal.setId(Integer.parseInt(document.get("id")));
        serviceTotal.setCategoryOne(highlighter.getBestFragment(analyzer, "categoryOne", document.get("categoryOne")) == null
                ? document.get("categoryOne") : highlighter.getBestFragment(analyzer, "categoryOne", document.get("categoryOne")));
        serviceTotal.setCategoryTwo(highlighter.getBestFragment(analyzer, "categoryTwo", document.get("categoryTwo")) == null
                ? document.get("categoryTwo") : highlighter.getBestFragment(analyzer, "categoryTwo", document.get("categoryTwo")));
        serviceTotal.setCategoryThree(highlighter.getBestFragment(analyzer, "categoryThree", document.get("categoryThree")) != null
                ? highlighter.getBestFragment(analyzer, "categoryThree", document.get("categoryThree")) : document.get("categoryThree"));
        serviceTotal.setInstitutionTitle(highlighter.getBestFragment(analyzer, "institutionTitle", document.get("institutionTitle")) != null
                ? highlighter.getBestFragment(analyzer, "institutionTitle", document.get("institutionTitle")) : document.get("institutionTitle"));
        serviceTotal.setServiceTitle(highlighter.getBestFragment(analyzer, "serviceTitle", document.get("serviceTitle")) != null
                ? highlighter.getBestFragment(analyzer, "serviceTitle", document.get("serviceTitle")) : document.get("serviceTitle"));
        serviceTotal.setServiceUrl(document.get("serviceUrl"));
        serviceTotal.setDetectionItem(highlighter.getBestFragment(analyzer, "detectionItem", document.get("detectionItem")) != null
                ? highlighter.getBestFragment(analyzer, "detectionItem", document.get("detectionItem")) : document.get("detectionItem"));
        serviceTotal.setServiceDetail(highlighter.getBestFragment(analyzer, "serviceDetail", document.get("serviceDetail")) != null
                ? highlighter.getBestFragment(analyzer, "serviceDetail", document.get("serviceDetail")) : document.get("serviceDetail"));
//        serviceTotal.setQualityScore(document.get("qualityScore"));
//        serviceTotal.setSpeedScore(document.get("speedScore"));
//        serviceTotal.setAttitudeScore(document.get("attitudeScore"));
//        serviceTotal.setPrice(document.get("price"));
//        serviceTotal.setCycle(document.get("cycle"));
        serviceTotal.setQualityScore(serviceTotalMapper.selectById(serviceTotal.getId()).getQualityScore());
        serviceTotal.setSpeedScore(serviceTotalMapper.selectById(serviceTotal.getId()).getSpeedScore());
        serviceTotal.setAttitudeScore(serviceTotalMapper.selectById(serviceTotal.getId()).getAttitudeScore());
        serviceTotal.setPrice(serviceTotalMapper.selectById(serviceTotal.getId()).getPrice());
        serviceTotal.setCycle(serviceTotalMapper.selectById(serviceTotal.getId()).getCycle());

        return serviceTotal;
    }
}
