package com.crawler.webapp.job.bean;

import com.crawler.webapp.proxyserver.bean.ProxyAssign;
import com.workbench.auth.user.entity.User;

import java.util.List;

/**
 * Created by SongCQ on 2017/7/31.
 */
public class JobInfoBean {

    private int job_id;
    private int user_id;
    private User user;
    private String job_name;
    private Integer is_valid = null;//0无效 1有效
    private String is_valid_cn = "未知";//0无效 1有效
    private int host_id;
    private int max_page_num;
    private int page_life_cycle;
    private int entry_page_id;
    private int job_cat_id;
    private int max_depth;
    private int crawl_src_type_id;//数据源类型
    private String start_urls;
    private int data_store_id;//数据存储编号
    private int job_schedule_id;
    private JobStatus jobStatus;
    private ProxyAssign proxyAssign;


    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public Integer getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(Integer is_valid) {

        if(is_valid==0){
            is_valid_cn = "无效";
        }else if(is_valid==1){
            is_valid_cn = "有效";
        }else{
            is_valid_cn = "未知";
        }
        this.is_valid = is_valid;
    }

    public int getHost_id() {
        return host_id;
    }

    public void setHost_id(int host_id) {
        this.host_id = host_id;
    }

    public int getMax_page_num() {
        return max_page_num;
    }

    public void setMax_page_num(int max_page_num) {
        this.max_page_num = max_page_num;
    }

    public int getPage_life_cycle() {
        return page_life_cycle;
    }

    public void setPage_life_cycle(int page_life_cycle) {
        this.page_life_cycle = page_life_cycle;
    }

    public int getEntry_page_id() {
        return entry_page_id;
    }

    public void setEntry_page_id(int entry_page_id) {
        this.entry_page_id = entry_page_id;
    }

    public int getJob_cat_id() {
        return job_cat_id;
    }

    public void setJob_cat_id(int job_cat_id) {
        this.job_cat_id = job_cat_id;
    }

    public int getMax_depth() {
        return max_depth;
    }

    public void setMax_depth(int max_depth) {
        this.max_depth = max_depth;
    }

    public int getCrawl_src_type_id() {
        return crawl_src_type_id;
    }

    public void setCrawl_src_type_id(int crawl_src_type_id) {
        this.crawl_src_type_id = crawl_src_type_id;
    }

    public String getStart_urls() {
        return start_urls;
    }

    public void setStart_urls(String start_urls) {
        this.start_urls = start_urls;
    }

    public int getData_store_id() {
        return data_store_id;
    }

    public void setData_store_id(int data_store_id) {
        this.data_store_id = data_store_id;
    }

    public int getJob_schedule_id() {
        return job_schedule_id;
    }

    public void setJob_schedule_id(int job_schedule_id) {
        this.job_schedule_id = job_schedule_id;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getIs_valid_cn() {
        return is_valid_cn;
    }

    public void setIs_valid_cn(String is_valid_cn) {
        this.is_valid_cn = is_valid_cn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProxyAssign getProxyAssign() {
        return proxyAssign;
    }

    public void setProxyAssign(ProxyAssign proxyAssign) {
        this.proxyAssign = proxyAssign;
    }
}
