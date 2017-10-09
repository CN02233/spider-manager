package com.crawler.webapp.search.service.imp;

import com.AbstractTestService;
import com.crawler.webapp.search.bean.SearchBean;
import com.crawler.webapp.search.service.PageSearchManageService;
import com.webapp.support.httpClient.HttpSendMessage;
import com.webapp.support.json.JsonSupport;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by SongCQ on 2017/9/27.
 */
public class PageSearchManageServiceImpTest extends AbstractTestService {

    @Resource
    private PageSearchManageService pageSearchManageService;

    @Test
    public void listJobCategory() throws Exception {
        pageSearchManageService.listJobCategory();
    }

    @Test
    public void doSearch() throws Exception {
        SearchBean searchBean = new SearchBean();
//        searchBean.setSearchContent("2017-01-09");
        List<Integer> jobs = new ArrayList<>();
//        jobs.add(2);
        jobs.add(4);
        searchBean.setJobIdList(jobs);
        Map<String, Object> allRe = pageSearchManageService.doSearch(searchBean);


        System.out.println(JsonSupport.objectToJson(allRe));
    }

}