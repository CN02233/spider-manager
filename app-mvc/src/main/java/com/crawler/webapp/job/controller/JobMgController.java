package com.crawler.webapp.job.controller;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.service.JobMgService;
import com.crawler.webapp.proxyserver.bean.ProxyServer;
import com.github.pagehelper.Page;
import com.google.common.base.Strings;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
import com.webapp.support.session.SessionSupport;
import com.workbench.auth.user.entity.User;
import com.workbench.spring.aop.annotation.JsonMsgParam;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SongCQ on 2017/7/31.
 */
@Controller
@RequestMapping("crawler/jobMg")
public class JobMgController {

    private Logger logger = LoggerFactory.getLogger(JobMgController.class);

    @Autowired
    private JobMgService jobMgService;

    @RequestMapping("pagingList")
    @ResponseBody
    @JsonpCallback
    public String pagingCrawlList(int currPage,int pageSize, JobInfoBean jobInfoBean){
        Page<JobInfoBean> crawListPage = jobMgService.pagingCrawlList(currPage, pageSize, jobInfoBean);
        PageResult pageResult = PageResult.pageHelperList2PageResult(crawListPage);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult);

        logger.debug("paging crawl list result :{}",result);
        return result;
    }

    @RequestMapping("listAllCraw")
    @ResponseBody
    @JsonpCallback
    public String listAllCraw(){
        List<JobInfoBean> allJob = jobMgService.listAllJob();
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,allJob);
        return result;
    }

    @RequestMapping("pagingListByHost")
    @ResponseBody
    @JsonpCallback
    public String pagingListByHost(int currPage,int pageSize,Integer host_id, String host_name){
        Page<JobInfoBean> crawListPage = jobMgService.pagingListByHost(currPage, pageSize,host_id, host_name);
        PageResult pageResult = PageResult.pageHelperList2PageResult(crawListPage);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult);

        logger.debug("paging crawl list result :{}",result);
        return result;
    }

    @RequestMapping("crawlSrcType")
    @ResponseBody
    @JsonpCallback
    public String crawlSrcType(){
        List<Map<String,Object>> resultFromDb = jobMgService.crawlSrcType();
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,resultFromDb);
        return result;
    }

    @RequestMapping("dataStore")
    @ResponseBody
    @JsonpCallback
    public String dataStore(){
        List<Map<String,Object>> resultFromDb = jobMgService.dataStore();
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,resultFromDb);
        return result;
    }

    @RequestMapping("jobScheduleList")
    @ResponseBody
    @JsonpCallback
    public String jobScheduleList(){
        List<Map<String,Object>> resultFromDb = jobMgService.jobScheduleList();
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,resultFromDb);
        return result;
    }

    @RequestMapping("jobHostList")
    @ResponseBody
    @JsonpCallback
    public String jobHostList(){
        List<Map<String,Object>> resultFromDb = jobMgService.jobHostList();
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"获取成功",null,resultFromDb);
        return result;
    }

    @RequestMapping("saveNewJob")
    @ResponseBody
    @JsonpCallback(isJsonRequest = true)
    public String saveNewJob(@JsonMsgParam(jsonName = "jobInfo",jsonObjTypes={JobInfoBean.class}) JobInfoBean jobInfo,
                             @JsonMsgParam(jsonName = "proxyServers",jsonObjTypes={String.class}) ArrayList<String> proxyServers){

        User user = SessionSupport.checkoutUserFromSession();
        jobInfo.setUser_id(user.getUser_id());
        jobMgService.saveNewJob(jobInfo,proxyServers);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
        return result;
    }

    @RequestMapping("updateJobInfo")
    @ResponseBody
    @JsonpCallback(isJsonRequest = true)
    public String updateJobInfo(@JsonMsgParam(jsonName = "jobInfo",jsonObjTypes={JobInfoBean.class}) JobInfoBean jobInfo,
                                @JsonMsgParam(jsonName = "proxyServers",jsonObjTypes={String.class}) ArrayList<String> proxyServers){
        jobMgService.updateJobInfo(jobInfo,proxyServers);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"保存成功",null,null);
        return result;
    }

    @RequestMapping("getCrawlAndProxy")
    @ResponseBody
    @JsonpCallback
    public String getCrawlAndProxy(int job_id){
        JobInfoBean crawlData = jobMgService.getCrawlData(job_id);
        List<ProxyServer> allProxyServerList = jobMgService.listAllProxyServerByJob(job_id);
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("crawlData",crawlData);
        resultMap.put("allProxyServerList",allProxyServerList);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"保存成功",null,resultMap);
        return result;
    }

    @RequestMapping("deleJob")
    @ResponseBody
    @JsonpCallback
    public String deleJob(int job_id){
        jobMgService.deleJob(job_id);
        String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"删除成功",null,null);
        return result;
    }

    @RequestMapping("startJob")
    @ResponseBody
    @JsonpCallback
    public String startJob(Integer job_id, Integer user_id){
        String startResult = jobMgService.startJob(job_id, user_id);
        if(!Strings.isNullOrEmpty(startResult)&&"DONE".equals(startResult)){
            String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"启动成功",null,null);
            return result;
        }else{
            String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"启动失败:"+startResult,null,null);
            return result;
        }

    }

    @RequestMapping("stopJob")
    @ResponseBody
    @JsonpCallback
    public String stopJob(Integer job_id, Integer user_id){

        String startResult = jobMgService.stopJob(job_id, user_id);
        if(!Strings.isNullOrEmpty(startResult)&&"DONE".equals(startResult)){
            String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"停止成功",null,null);
            return result;
        }else{
            String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"停止失败:"+startResult,null,null);
            return result;
        }
    }

    @RequestMapping("updateJob")
    @ResponseBody
    @JsonpCallback
    public String updateJob(Integer job_id, Integer user_id){
        String startResult = jobMgService.updateJob(job_id, user_id);
        if(!Strings.isNullOrEmpty(startResult)&&"DONE".equals(startResult)){
            String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"更新成功",null,null);
            return result;
        }else{
            String result = JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS,"更新失败:"+startResult,null,null);
            return result;
        }
    }

}
