package com.crawler.webapp.job.service.imp;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.bean.JobStatus;
import com.crawler.webapp.job.dao.IJobMgDao;
import com.crawler.webapp.job.service.JobMgService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SongCQ on 2017/7/31.
 */
@Service("jobMgService")
public class JobMgServiceImp implements JobMgService {

    @Autowired
    private IJobMgDao iJobMgDao;

    @Override
    public Page<JobInfoBean> pagingCrawlList(int currPage,int pageSize, JobInfoBean jobInfoBean) {
        Page<JobInfoBean> resultPage = iJobMgDao.pagingCrawlList(currPage, pageSize, jobInfoBean);
        for(JobInfoBean pageData : resultPage){
            JobStatus jobStatus = pageData.getJobStatus();
            if(jobStatus!=null){}
            else pageData.setJobStatus(new JobStatus());
        }
        return resultPage;
    }
}
