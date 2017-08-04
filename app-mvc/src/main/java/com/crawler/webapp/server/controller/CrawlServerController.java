package com.crawler.webapp.server.controller;

import com.crawler.webapp.server.bean.CrawlServer;
import com.crawler.webapp.server.service.CrawlServerService;
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
 * Created by SongCQ on 2017/8/3.
 */
@Controller
@RequestMapping("crawl/server")
public class CrawlServerController {

    @Autowired
    private CrawlServerService crawlServerService;

    @RequestMapping("pagingServer")
    @ResponseBody
    @JsonpCallback
    public String pagingServer(int currPage,int pageSize,String host_name){
        Page<CrawlServer> pageRs = crawlServerService.pagingServer(currPage,pageSize,host_name);
        PageResult pageResult = PageResult.pageHelperList2PageResult(pageRs);
        String result = JsonpSupport.makeJsonpResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult);
        return result;
    }

}
