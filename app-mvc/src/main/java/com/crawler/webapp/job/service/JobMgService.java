package com.crawler.webapp.job.service;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.github.pagehelper.Page;

/**
 * Created by SongCQ on 2017/7/31.
 */
public interface JobMgService {

    Page<JobInfoBean> pagingCrawlList(int currPage,int pageSize, JobInfoBean jobInfoBean);

}
