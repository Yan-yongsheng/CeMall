package com.demo.shop.index;

import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.mapper.ServiceTotalMapper;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class LuceneIndex implements ApplicationRunner {
    @Autowired
    private ServiceTotalMapper serviceTotalMapper;

    @Override
    public void run(ApplicationArguments args) {
//        createIndexAll();
    }

    private void createIndexAll() {
        File folder = new File("./dir");
        if (folder.exists() && folder.isDirectory()) {
            System.out.println("索引已存在！！！");
            return;
        }
        List<ServiceTotal> serviceTotals = serviceTotalMapper.selectList(null);
        createIndex(serviceTotals);
    }

    public void createIndex(List<ServiceTotal> serviceTotalList) {
        try {
            System.out.println("创建索引！！！");
            List<Document> documents = new ArrayList<>();
            /**
             * 根据是否分词，是否索引，是否存储来创建域
             * 需要搜索的话就必须要索引，需要展示的话必须存储，分不分词看情况
             */
            for (ServiceTotal service : serviceTotalList) {
                Document document = new Document();
                //id不分词，创建索引，存储
                document.add(new StringField("id", service.getId() + "", Field.Store.YES));
                //categoryOne分词，创建索引，存储
                document.add(new TextField("categoryOne", service.getCategoryOne(), Field.Store.YES));
                //categoryTwo分词，创建索引，存储
                document.add(new TextField("categoryTwo", service.getCategoryTwo(), Field.Store.YES));
                //categoryThree分词，创建索引，存储
                document.add(new TextField("categoryThree", service.getCategoryThree(), Field.Store.YES));
                //serviceTitle分词，创建索引，存储
                document.add(new TextField("serviceTitle", service.getServiceTitle(), Field.Store.YES));
                //serviceUrl不分词，不创建索引，存储
                document.add(new StoredField("serviceUrl", service.getServiceUrl()));
                //detectionItem分词，创建索引，存储
                document.add(new TextField("detectionItem", service.getDetectionItem(), Field.Store.YES));
                //institutionTitle分词，创建索引，存储
                document.add(new TextField("institutionTitle", service.getInstitutionTitle(), Field.Store.YES));
                //institutionUrl不创建域
                //serviceDetail分词，创建索引，存储
                document.add(new TextField("serviceDetail", service.getServiceDetail(), Field.Store.YES));
//                document.add(new StoredField("qualityScore",service.getQualityScore()));
//                document.add(new StoredField("speedScore",service.getSpeedScore()));
//                document.add(new StoredField("attitudeScore",service.getAttitudeScore()));
//                document.add(new StoredField("price",service.getPrice()));
//                document.add(new StoredField("cycle",service.getCycle()));
                documents.add(document);
            }

            IKAnalyzer analyzer = new IKAnalyzer();
            FSDirectory directory = FSDirectory.open(Paths.get("./dir"));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);
            for (Document document : documents) {
                indexWriter.addDocument(document);
            }
            indexWriter.close();
            System.out.println("索引创建完毕！！！");
        } catch (Exception e) {
            System.out.println("索引创建失败！！！");
            e.printStackTrace();
        }
    }
}
