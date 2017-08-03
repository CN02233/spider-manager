package com.crawler.webapp.job.service;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SongCQ on 2017/7/31.
 */
public interface JobMgService {

    Page<JobInfoBean> pagingCrawlList(int currPage,int pageSize, JobInfoBean jobInfoBean);

    List<Map<String, Object>> crawlSrcType();

    List<Map<String,Object>> dataStore();

    List<Map<String,Object>> jobScheduleList();

    List<Map<String,Object>> jobHostList();

    void saveNewJob(JobInfoBean jobInfo, List<String> proxyServers);
}
