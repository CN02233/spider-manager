package com.crawler.webapp.job.controller;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.service.JobMgService;
import com.github.pagehelper.Page;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.page.PageResult;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by SongCQ on 2017/7/31.
 */
@Controller
@RequestMapping("crawler/jobMg")
public class JobMgController {

    @Autowired
    private JobMgService jobMgService;

    @RequestMapping("pagingList")
    @ResponseBody
    @JsonpCallback
    public String pagingCrawlList(int currPage,int pageSize, JobInfoBean jobInfoBean){
        Page<JobInfoBean> crawListPage = jobMgService.pagingCrawlList(currPage, pageSize, jobInfoBean);
        PageResult pageResult = PageResult.pageHelperList2PageResult(crawListPage);
        String result = JsonpSupport.makeJsonpResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult);
        return result;
    }

}
