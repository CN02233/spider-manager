package com.crawler.webapp.job.service.imp;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.bean.JobStatus;
import com.crawler.webapp.job.dao.IJobMgDao;
import com.crawler.webapp.job.service.JobMgService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> crawlSrcType() {
        return iJobMgDao.crawlSrcType();
    }

    @Override
    public List<Map<String, Object>> dataStore() {
        return iJobMgDao.dataStore();
    }

    @Override
    public List<Map<String, Object>> jobScheduleList() {
        return iJobMgDao.jobScheduleList();
    }

    @Override
    public List<Map<String, Object>> jobHostList() {
        return iJobMgDao.jobHostList();
    }
}
