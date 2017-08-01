package com.crawler.webapp.job.dao;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.bean.JobStatus;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by SongCQ on 2017/7/31.
 */
public interface IJobMgDao {

    @Select("<script>" +
            "select * from crawl_job where " +
            " is_valid=#{jobInfoBean.is_valid}" +
            "<if test='jobInfoBean.job_name!=null'> " +
            "   and job_name like concat('%',#{jobInfoBean.job_name},'%')" +
            " </if> " +
            "</script>")
    @Results({@Result(property = "jobStatus",column = "job_id",
            many = @Many(select="com.crawler.webapp.job.dao.IJobMgDao.getJobStatus")),
            @Result(property = "user",column = "user_id",
                    many = @Many(select="com.workbench.auth.user.dao.IUserServiceDao.getUserByUserId"))})
    @Options(useCache = false)
    Page<JobInfoBean> pagingCrawlList(@Param("currPage") int currPage,@Param("pageSize") int pageSize,@Param("jobInfoBean") JobInfoBean jobInfoBean);

    @Select("select max(start_time) max_start_time,cs.* from crawl_status cs where job_id = #{job_id}")
    @Options(useCache = false)
    JobStatus getJobStatus(int job_id);

}
