package com.crawler.webapp.search.service.imp;

import com.crawler.webapp.job.bean.CrawlerServers;
import com.crawler.webapp.job.bean.JobCategory;
import com.crawler.webapp.search.bean.SearchBean;
import com.crawler.webapp.search.dao.PageSearchManageDao;
import com.crawler.webapp.search.service.PageSearchManageService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.webapp.support.httpClient.HttpSendMessage;
import com.webapp.support.json.JsonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SongCQ on 2017/9/27.
 */
@Service("pageSearchManageService")
public class PageSearchManageServiceImp implements PageSearchManageService {

    private Logger logger = LoggerFactory.getLogger(PageSearchManageServiceImp.class);

    @Autowired
    private PageSearchManageDao pageSearchManageDao;

    @Autowired
    private CrawlerServers crawlerServers;

    @Override
    public List<JobCategory> listJobCategory() {
        List<JobCategory> resultList = pageSearchManageDao.listJobCategory();
        return resultList;
    }

    @Override
    public Map<String, Object> doSearch(SearchBean searchBean) {
        List<Integer> jobIds = searchBean.getJobIdList();

        Map<String,Object> responseMap = new HashMap<>();


//        User user = SessionSupport.checkoutUserFromSession();

        if(jobIds!=null&&jobIds.size()>0){
            for(Integer jobId:jobIds){
                String solrServerAddres = crawlerServers.getSolrAddres();
                StringBuilder builder = new StringBuilder();
                builder.append(solrServerAddres);
                builder.append("/job_");
//                builder.append(user.getUser_id());
                builder.append("1");
                builder.append("_");
                builder.append(jobId);
                builder.append("/select");

                Map<String,Object> paramMap = new HashMap<>();
                if(!Strings.isNullOrEmpty(searchBean.getSearchContent())){
                    paramMap.put("q",new StringBuilder().append("page_content:").append(searchBean.getSearchContent()).toString());
                }else{
                    paramMap.put("q","*:*");
                }
                paramMap.put("wt","json");
                Map<String, String> pagingMap = searchBean.getPagingMap();
                paramMap.put("start",pagingMap.get(jobId.toString()));
                paramMap.put("rows",searchBean.getRows());
                String response = HttpSendMessage.postHttpRequest4Str(builder.toString(), paramMap);
                logger.info("send to solr service,param-->{} /r solr service response-->{}",paramMap.toString(),response);
                if(Strings.isNullOrEmpty(response)){
                    responseMap.put(jobId.toString(), Lists.newArrayList());
                    continue;
                }
                HashMap resultMap = JsonSupport.jsonToMap(response);
                Map<String,Object> responseMapTmp = (Map<String,Object>) resultMap.get("response");
                List<Map<String,Object>> docsList = (List<Map<String, Object>>) responseMapTmp.get("docs");
                Integer numFound = (Integer) responseMapTmp.get("numFound");
                for(Map<String,Object> docsMap:docsList){
                    String page_source = (String) docsMap.get("page_source");
                    page_source = page_source.replaceAll("/r","");
                    page_source = page_source.replaceAll("/n","");
                    if(page_source.length()>200)
                        docsMap.put("page_source",page_source.substring(0,200));

                    String page_content = (String) docsMap.get("page_content");
                    if(page_content.length()>200)
                        docsMap.put("page_content",page_content.substring(0,200));

                    String id = (String) docsMap.get("id");
                    String[] idSplit = id.split(",");
                    if(idSplit!=null&&idSplit.length>1){
                        String url = idSplit[0];
                        docsMap.put("url",url);
                    }else
                        docsMap.put("url",id);
                }
                responseMap.put(jobId.toString(),docsList);
                responseMap.put(jobId+"_num_found",numFound);

            }
        }

        return responseMap;
    }
}
