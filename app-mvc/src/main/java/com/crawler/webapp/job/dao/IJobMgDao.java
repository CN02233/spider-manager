package com.crawler.webapp.job.dao;

import com.crawler.webapp.job.bean.JobInfoBean;
import com.crawler.webapp.job.bean.JobStatus;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by SongCQ on 2017/7/31.
 */
public interface IJobMgDao {

    @Select("<script>" +
            "select * from crawl_job  " +
            "<where>" +
            "<if test='jobInfoBean.is_valid!=null  '> " +
            "   is_valid=#{jobInfoBean.is_valid}" +
            "</if>"+
            "<if test='jobInfoBean.job_name!=null  '> " +
            "   and job_name like concat('%',#{jobInfoBean.job_name},'%')" +
            " </if> " +
            "</where>" +
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

    @Select("select * from crawl_src_type")
    @Options(useCache = false)
    List<Map<String, Object>> crawlSrcType();

    @Select("select * from data_store")
    @Options(useCache = false)
    List<Map<String,Object>> dataStore();

    @Select("select * from job_schedule")
    @Options(useCache = false)
    List<Map<String,Object>> jobScheduleList();

    @Select("select * from crawl_host")
    @Options(useCache = false)
    List<Map<String,Object>> jobHostList();

    @Insert("insert into crawl_job (job_id,user_id,job_name,is_valid,host_id,max_page_num,page_life_cycle," +
            "entry_page_id,job_cat_id,max_depth,crawl_src_type_id,start_urls,data_store_id,job_schedule_id) values " +
            "(#{job_id},#{user_id},#{job_name},#{is_valid},#{host_id},#{max_page_num},#{page_life_cycle}," +
            "#{entry_page_id},#{job_cat_id},#{max_depth},#{crawl_src_type_id},#{start_urls},#{data_store_id},#{job_schedule_id})")
    void saveJobInfo(JobInfoBean jobInfo);


    @Insert("insert into poxy_assign (proxy_server_id,job_id,user_id ) values (#{proxy_server_id},#{job_id},#{user_id})")
    void saveProxyServer(@Param("proxy_server_id") String proxy_server_id,@Param("job_id") int job_id,@Param("user_id") int user_id);
}
