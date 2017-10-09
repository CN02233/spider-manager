package com.crawler.webapp.search.controller;

import com.crawler.webapp.job.bean.JobCategory;
import com.crawler.webapp.search.bean.SearchBean;
import com.crawler.webapp.search.service.PageSearchManageService;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by SongCQ on 2017/9/27.
 */

@Controller
@RequestMapping("search/pageSearch")
public class PageSearchManageController {

    @Autowired
    private PageSearchManageService pageSearchManageService;

    @RequestMapping("listJobTypes")
    @JsonpCallback
    @ResponseBody
    public String listJobTypes(){
        List<JobCategory> jobCataList = pageSearchManageService.listJobCategory();
        String responseStr = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "获取成功", null, jobCataList);

        return responseStr;
    }

    @RequestMapping("doSearch")
    @JsonpCallback
    @ResponseBody
    public String doSearch(SearchBean searchBean){
        Map<String, Object> reponseFromSolr = pageSearchManageService.doSearch(searchBean);
        String responseStr = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "获取成功", null, reponseFromSolr);
        return responseStr;
    }

}
