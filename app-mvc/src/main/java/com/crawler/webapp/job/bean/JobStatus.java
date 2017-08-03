package com.crawler.webapp.job.bean;

import java.util.Date;

/**
 * Created by SongCQ on 2017/7/31.
 */
public class JobStatus {

    private int user_id;

    private int job_id;

    private Date start_time;

    private int run_status;

    private String run_status_cn = "未知";

    private int download_page_num;

    private int pending_page_num;

    private int error_page_num;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public int getRun_status() {
        return run_status;
    }

    public void setRun_status(int run_status) {
        switch (run_status){
            case 0:run_status_cn = "开始";
            case 1:run_status_cn = "运行";
            case 2:run_status_cn = "结束";
        }
        this.run_status = run_status;
    }

    public int getDownload_page_num() {
        return download_page_num;
    }

    public void setDownload_page_num(int download_page_num) {
        this.download_page_num = download_page_num;
    }

    public int getPending_page_num() {
        return pending_page_num;
    }

    public void setPending_page_num(int pending_page_num) {
        this.pending_page_num = pending_page_num;
    }

    public int getError_page_num() {
        return error_page_num;
    }

    public void setError_page_num(int error_page_num) {
        this.error_page_num = error_page_num;
    }

    public String getRun_status_cn() {
        return run_status_cn;
    }

    public void setRun_status_cn(String run_status_cn) {


        this.run_status_cn = run_status_cn;
    }
}
