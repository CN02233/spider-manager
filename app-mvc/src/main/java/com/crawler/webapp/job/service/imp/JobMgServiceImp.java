package com.crawler.webapp.job.service.imp;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.bean.JobStatus;
import com.crawler.webapp.job.dao.IJobMgDao;
import com.crawler.webapp.job.service.JobMgService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveNewJob(JobInfoBean jobInfo, List<String> proxyServers) {
        SimpleDateFormat format = new SimpleDateFormat("ssSSS");
        StringBuilder builder = new StringBuilder();
        builder.append(format.format(Calendar.getInstance().getTime()));
        builder.append(new Random().nextInt(50));
        int job_id = new Integer(builder.toString());
        job_id = job_id<<(new Random().nextInt(5));

        if(jobInfo.getIs_valid()==null)
            jobInfo.setIs_valid(1);

        jobInfo.setJob_id(job_id);

        iJobMgDao.saveJobInfo(jobInfo);
        if(proxyServers!=null){
            for(String proxyServerId:proxyServers){
                iJobMgDao.saveProxyServer(proxyServerId,job_id,jobInfo.getUser_id());

            }
        }
    }
}
