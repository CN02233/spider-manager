package com.crawler.webapp.crawlerpage.controller;

import com.crawler.webapp.crawlerpage.service.CrawlerPageMgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SongCQ on 2017/7/25.
 */

@Controller
@RequestMapping("crawler/pageMg")
public class CrawlerPageMgController {

    @Autowired
    private CrawlerPageMgService crawlerPageMgService;

    @RequestMapping("pagingCrawlerPage")
    @ResponseBody
    public String listCrawlerPageByPaging(int currPage, int pageSize, HttpServletRequest request){
        return null;
    }

    public String viewCrawlerPageData(){
        return null;
    }

    public String newSaveCrawlerPage(){
        return null;
    }

    public String updateSaveCrawlerPage(){
        return null;
    }

}
