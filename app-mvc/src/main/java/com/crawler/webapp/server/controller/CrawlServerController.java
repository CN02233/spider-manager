package com.crawler.webapp.server.controller;

import com.crawler.webapp.server.bean.CrawlServer;
import com.crawler.webapp.server.service.CrawlServerService;
import com.github.pagehelper.Page;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
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
@RequestMapping("crawler/server")
public class CrawlServerController {

    @Autowired
    private CrawlServerService crawlServerService;

    @RequestMapping("pagingServer")
    @ResponseBody
    @JsonpCallback
    public String pagingServer(int currPage,int pageSize,String host_name){
        Page<CrawlServer> pageRs = crawlServerService.pagingServer(currPage,pageSize,host_name);
        PageResult pageResult = PageResult.pageHelperList2PageResult(pageRs);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult);
        return result;
    }

    @RequestMapping("saveNewServer")
    @ResponseBody
    @JsonpCallback
    public String saveNewServer(CrawlServer crawlServer){
        crawlServerService.saveNewServer(crawlServer);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
        return result;
    }

    @RequestMapping("updateServer")
    @ResponseBody
    @JsonpCallback
    public String updateServer(CrawlServer crawlServer){
        crawlServerService.updateServer(crawlServer);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
        return result;
    }

    @RequestMapping("getServer")
    @ResponseBody
    @JsonpCallback
    public String getServer(int host_id){
        CrawlServer serverData = crawlServerService.getServer(host_id);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,serverData);
        return result;
    }

    @RequestMapping("delServer")
    @ResponseBody
    @JsonpCallback
    public String delServer(Integer host_id){
        crawlServerService.delServer(host_id);
        String jsonpResult = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "删除成功", null, null);
        return jsonpResult;
    }

}
