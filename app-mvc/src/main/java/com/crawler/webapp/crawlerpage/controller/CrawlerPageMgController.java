package com.crawler.webapp.crawlerpage.controller;

import com.crawler.webapp.crawlerpage.bean.CrawlerPage;
import com.crawler.webapp.crawlerpage.bean.PageField;
import com.crawler.webapp.crawlerpage.bean.PageLink;
import com.crawler.webapp.crawlerpage.service.CrawlerPageMgService;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.page.PageResult;
import com.webapp.support.session.SessionSupport;
import com.workbench.auth.user.entity.User;
import com.workbench.spring.aop.annotation.JsonMsgParam;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    @JsonpCallback
    public PageResult listCrawlerPageByPaging(int currPage, int pageSize){
        PageResult pageResult = PageResult.pageHelperList2PageResult(
                crawlerPageMgService.listCrawlerPageByPaging(currPage, pageSize));
        return pageResult;
    }

    @RequestMapping("craPageData")
    @ResponseBody
    @JsonpCallback
    public JsonResult craPageData(int page_id, int job_id, int user_id){
        return  JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"获取成功",null,
                crawlerPageMgService.craPageData(page_id, job_id, user_id));

    }

    @RequestMapping("listPageLink")
    @ResponseBody
    @JsonpCallback
    public JsonResult listPageLink(int page_id, int job_id, int user_id) {

        return  JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"获取成功",null,
                crawlerPageMgService.listPageLink(page_id, job_id, user_id));
    }

    @RequestMapping("listPageField")
    @ResponseBody
    @JsonpCallback
    public JsonResult listPageField(int page_id, int job_id, int user_id) {
        return  JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"获取成功",null,
                crawlerPageMgService.listPageField(page_id, job_id, user_id));
    }

    /*
        新增
     */
    @RequestMapping("newSaveCrawlerPage")
    @ResponseBody
    @JsonpCallback
    public JsonResult newSaveCrawlerPage(CrawlerPage crawlerPage){
        User user = SessionSupport.checkoutUserFromSession();
        crawlerPage.setUser_id(user.getUser_id());
        crawlerPageMgService.newSaveCrawlerPage(crawlerPage);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
    }

    @RequestMapping("newSaveFields")
    @ResponseBody
    @JsonpCallback(isJsonRequest = true)
    public JsonResult newSaveFields(@JsonMsgParam(jsonObjTypes = PageField.class)
                                                ArrayList<PageField> pageFields){
        crawlerPageMgService.newSavePageFields(pageFields);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
    }

    @RequestMapping("newSaveLinks")
    @ResponseBody
    @JsonpCallback(isJsonRequest = true)
    public JsonResult newSaveLinks(@JsonMsgParam(jsonObjTypes = PageLink.class)
            List<PageLink> pageLinks){
        crawlerPageMgService.newSavePageLinks(pageLinks);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
    }

    /*
        更新
     */
    @RequestMapping("updateCrawlerPage")
    @ResponseBody
    @JsonpCallback
    public JsonResult updateCrawlerPage(CrawlerPage crawlerPage){
        crawlerPageMgService.updateCrawlerPage(crawlerPage);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
    }

    @RequestMapping("updatePageLinks")
    @ResponseBody
    @JsonpCallback(isJsonRequest = true)
    public JsonResult updatePageLinks(@JsonMsgParam(jsonObjTypes = PageLink.class)
                                                  ArrayList<PageLink> pageLinks){
        crawlerPageMgService.updatePageLinks(pageLinks);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
    }

    @RequestMapping("updatePageFields")
    @ResponseBody
    @JsonpCallback(isJsonRequest = true)
    public JsonResult updatePageFields(@JsonMsgParam(jsonObjTypes = PageField.class)
                                                   ArrayList<PageField> pageFields){
        crawlerPageMgService.updatePageFields(pageFields);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
    }

    /*
        删除
     */
    @RequestMapping("deleteCrawlerPage")
    @ResponseBody
    @JsonpCallback
    public JsonResult deleteCrawlerPage(int page_id, int job_id, int user_id){
        crawlerPageMgService.deleteCrawlerPage(page_id, job_id, user_id);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"删除成功",null,null);
    }

    @RequestMapping("deletePageField")
    @ResponseBody
    @JsonpCallback
    public JsonResult deletePageField(int field_id,int page_id, int job_id, int user_id){
        crawlerPageMgService.removePageFields(field_id, page_id, job_id, user_id);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"删除成功",null,null);
    }

    @RequestMapping("deletePageLink")
    @ResponseBody
    @JsonpCallback
    public JsonResult deletePageLink(int link_id,int page_id, int job_id, int user_id){
        crawlerPageMgService.removePageLink(link_id, page_id, job_id, user_id);
        return JsonpSupport.makeJsonpResult(JsonResult.RESULT.SUCCESS,"删除成功",null,null);
    }

}
