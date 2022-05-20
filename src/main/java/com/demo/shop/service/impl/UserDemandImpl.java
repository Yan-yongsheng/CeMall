package com.demo.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.ServiceTotal;
import com.demo.shop.entity.UserDemand;
import com.demo.shop.entity.add.UserDemandAdd;
import com.demo.shop.entity.find.UserDemandResult;
import com.demo.shop.mapper.ServiceTotalMapper;
import com.demo.shop.mapper.UserDemandMapper;
import com.demo.shop.service.ISearchService;
import com.demo.shop.service.IUserDemand;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDemandImpl extends ServiceImpl<UserDemandMapper, UserDemand> implements IUserDemand {
    @Autowired
    private UserDemandMapper userDemandMapper;

    @Autowired
    private ISearchService searchService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceTotalMapper serviceTotalMapper;

    @Override
    public ReturnData<String> addUserDemand(UserDemandAdd userDemandAdd) {
        try {
            UserDemand userDemand = new UserDemand();
            BeanUtils.copyProperties(userDemandAdd, userDemand);
            userDemandMapper.insert(userDemand);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "需求发布成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "需求发布失败！");
        }
    }

    @Override
    public ReturnData<List<UserDemand>> findUserDemandList() {
        try {
            List<UserDemand> userDemands = userDemandMapper.selectList(null);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), userDemands);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }

    @Override
    public ReturnData<UserDemand> findUserDemandById(int id) {
        try {
            UserDemand userDemand = userDemandMapper.selectById(id);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), userDemand);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }

    @Override
    public ReturnData<UserDemandResult> getUserDemandResult(int id) {
        try {
            UserDemandResult userDemandResult = new UserDemandResult();
            UserDemand userDemand = userDemandMapper.selectById(id);
            userDemandResult.setUserDemand(userDemand);
            List<String> words = searchService.getIkResolveWords(userDemand.getDemand());
            List<String> taskList = new ArrayList<>();
            String body = words.get(0);
            for (int i = 1; i < words.size(); i++) {
                taskList.add(body + words.get(i));
            }
            //拆解出来只有一个需求的话，将body直接加入到demandList中
            if (taskList.size() == 0) {
                taskList.add(body);
            }

            System.out.println(taskList);

            List<List<Integer>> docIdForTask = new ArrayList<>();
            //所有的候选服务集合
            Map<Integer, ServiceTotal> docIdToService = new HashMap<>();
            for (String task : taskList) {
                TopDocs topDocs = searchService.getTopDocs(task, 30);
                ScoreDoc[] scoreDocs = topDocs.scoreDocs;
                List<Integer> docIds = new ArrayList<>();
                for (ScoreDoc scoreDoc : scoreDocs) {
                    docIds.add(scoreDoc.doc);
                    if (!docIdToService.containsKey(scoreDoc.doc)) {
                        docIdToService.put(scoreDoc.doc, searchService.getServiceTotalFromDocId(scoreDoc.doc));
                    } else {
                        combineHighlighterText(docIdToService.get(scoreDoc.doc), searchService.getServiceTotalFromDocId(scoreDoc.doc));
                    }
                }
                docIdForTask.add(docIds);
            }

            System.out.println(docIdForTask);

            List<List<Integer>> serviceGroupList = new ArrayList<>();
            dfs(0, docIdForTask, new ArrayList<>(), serviceGroupList);

            Set<Set<Integer>> serviceGroupSet = new HashSet<>();
            for (List<Integer> serviceGroup : serviceGroupList) {
                serviceGroupSet.add(new HashSet<>(serviceGroup));
            }
//            System.out.println(serviceGroupSet);
//            System.out.println(serviceGroupSet.size());

            //去除重复包含的情况！！！
            List<Set<Integer>> serviceGroupSetList = new ArrayList<>(serviceGroupSet);
            boolean[] contained = new boolean[serviceGroupSetList.size()];
            int i = 0;
            while (i < serviceGroupSetList.size()) {
                int j = 0;
                Set<Integer> setI = serviceGroupSetList.get(i);
                while (j < serviceGroupSetList.size()) {
                    if (j != i && serviceGroupSetList.get(j).containsAll(setI)) {
                        contained[j] = true;
                    }
                    j++;
                }
                i++;
            }
            List<Set<Integer>> serviceGroups = new ArrayList<>();
            for (int k = 0; k < contained.length; k++) {
                if (!contained[k]) {
                    serviceGroups.add(serviceGroupSetList.get(k));
                }
            }
            System.out.println(serviceGroups);

            /**
             * 一边遍历一边删除bug很多
             */
//            int i = 0;
//            while (i < serviceGroupSetList.size()) {
//                int j = 0;
//                Set<Integer> setI = serviceGroupSetList.get(i);
//                while (j < serviceGroupSetList.size()) {
//                    if (serviceGroupSetList.get(j).containsAll(setI)) {
//                        serviceGroupSetList.remove(j);
//                    } else {
//                        j++;
//                    }
//                }
//                i++;
//            }
//            System.out.println(serviceGroupSetList);
//            System.out.println(serviceGroupSetList.size());

            int groupNum = 0;
            List<List<ServiceTotal>> resultList = new ArrayList<>();

            for (Set<Integer> serviceGroup : serviceGroups) {
                if (serviceGroup.size() > 1) {
                    groupNum++;
                }
                List<ServiceTotal> result = new ArrayList<>();
                for (Integer docId : serviceGroup) {
                    result.add(docIdToService.get(docId));
                }
                resultList.add(result);
            }

            userDemandResult.setResultList(resultList);
            userDemandResult.setSum(resultList.size());
            userDemandResult.setGroupSum(groupNum);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), userDemandResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), null);
        }
    }

    @Override
    public ReturnData<UserDemandResult> getUserLimitResult(int id) {
        try{
            UserDemand userDemand = userDemandMapper.selectById(id);
            Map<String,Object> paramMap=new HashMap<>();
            List<Integer> taskList=null;
            if(id==10){
                taskList=new ArrayList<>();
                taskList.add(10);
                taskList.add(11);
                taskList.add(16);
            }
            if(id==11){
                taskList=new ArrayList<>();
                taskList.add(1108);
                taskList.add(851);
            }
            paramMap.put("task_id_list",taskList);
            List<Integer> limitList=new ArrayList<>();
            limitList.add(userDemand.getPrice());
            limitList.add(userDemand.getCycle());
            paramMap.put("user_qos_needed",limitList);
            ResponseEntity<JSONObject> entity = restTemplate.postForEntity("http://10.129.2.31:5555", paramMap, JSONObject.class);
            Map data = entity.getBody().getObject("data",Map.class);
            List<List<Integer>> result = (List) data.get("result");
            UserDemandResult userDemandResult = new UserDemandResult();
            List<List<ServiceTotal>> resultList=new ArrayList<>();
            int groupSum=0;
            for (List<Integer> list : result) {
                List<ServiceTotal> resultListTmp=new ArrayList<>();
                if(list.size()>1){
                    groupSum+=1;
                }
                for (Integer serviceId : list) {
                    resultListTmp.add(serviceTotalMapper.selectById(serviceId));
                }
                resultList.add(resultListTmp);
            }
            userDemandResult.setResultList(resultList);
            userDemandResult.setSum(resultList.size());
            userDemandResult.setGroupSum(groupSum);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),StateCode.SUCCESS.getMsg(),userDemandResult);
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),StateCode.FAIL.getMsg(),null);
        }
    }

    @Override
    public ReturnData<String> submitComment(String id, String score, String commentContent) {
        try{
            Map<String,String> paramMap=new HashMap<>();
            paramMap.put("goods_id",id);
            paramMap.put("score",score);
            paramMap.put("comment_details",commentContent);
            ResponseEntity<JSONObject> entity = restTemplate.postForEntity("http://10.129.2.31:5000/add_comment", paramMap, JSONObject.class);
            double qualityScore=(double) entity.getBody().get("quality_score");
            double speedScore=(double) entity.getBody().get("speed_score");
            double attitudeScore=(double) entity.getBody().get("attitude_score");
            userDemandMapper.submitComment(Integer.parseInt(id),(int) qualityScore,(int)speedScore,(int)attitudeScore);
            return new ReturnData<>(StateCode.SUCCESS.getCode(),StateCode.SUCCESS.getMsg(),"发表评价成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new ReturnData<>(StateCode.FAIL.getCode(),StateCode.FAIL.getMsg(),"发表评价失败！");
        }
    }

    private void dfs(int index, List<List<Integer>> docIdForTask, List<Integer> tmp, List<List<Integer>> serviceGroupList) {
        if (index >= docIdForTask.size()) {
            serviceGroupList.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < docIdForTask.get(index).size(); i++) {
            tmp.add(docIdForTask.get(index).get(i));
            dfs(index + 1, docIdForTask, tmp, serviceGroupList);
            tmp.remove(docIdForTask.get(index).get(i));
        }
    }

    //将serviceTitle和serviceDetail中多次命中的实体都标红
    private void combineHighlighterText(ServiceTotal service1, ServiceTotal service2) {
        String serviceDetailText1 = service1.getServiceDetail();
        String serviceDetailText2 = service2.getServiceDetail();
        String pattern = "<span style='color: red;font-weight: bolder;'>(.*?)</span>";
        Pattern p = Pattern.compile(pattern);
        Matcher serviceDetailMatcher = p.matcher(serviceDetailText2);
        Set<String> serviceDetailMatchSet = new HashSet<>();
        while (serviceDetailMatcher.find()) {
            serviceDetailMatchSet.add(serviceDetailMatcher.group(1));
        }
        for (String matchPattern : serviceDetailMatchSet) {
            serviceDetailText1 = Pattern.compile(matchPattern).matcher(serviceDetailText1).replaceAll("<span style='color: red;font-weight: bolder;'>" + matchPattern + "</span>");
        }
        service1.setServiceDetail(serviceDetailText1);

        String serviceTitleText1 = service1.getServiceTitle();
        String serviceTitleText2 = service2.getServiceTitle();
        Matcher serviceTitleMatcher = p.matcher(serviceTitleText2);
        Set<String> serviceTitleMatchSet=new HashSet<>();
        while (serviceTitleMatcher.find()){
            serviceTitleMatchSet.add(serviceTitleMatcher.group(1));
        }
        for (String matchPattern : serviceTitleMatchSet) {
            serviceTitleText1=Pattern.compile(matchPattern).matcher(serviceTitleText1).replaceAll("<span style='color: red;font-weight: bolder;'>" + matchPattern + "</span>");
        }
        service1.setServiceTitle(serviceTitleText1);
    }
}
