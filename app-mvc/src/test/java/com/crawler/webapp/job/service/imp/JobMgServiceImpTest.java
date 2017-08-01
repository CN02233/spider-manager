package com.crawler.webapp.job.service.imp;

import com.AbstractTestService;
import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.service.JobMgService;
import com.github.pagehelper.Page;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by SongCQ on 2017/7/31.
 */
public class JobMgServiceImpTest extends AbstractTestService{

    @Resource
    private JobMgService jobMgService;

    @Test
    public void pagingCrawlList() throws Exception {
        JobInfoBean jobInfoBean = new JobInfoBean();
//        jobInfoBean.setIs_valid(1);
        jobInfoBean.setJob_name("新浪");

        Page<JobInfoBean> res = jobMgService.pagingCrawlList(1, 10, jobInfoBean);

        System.out.print("");
    }

}