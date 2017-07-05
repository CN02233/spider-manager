package com.webapp.spider.sys;

/**
 * Created by pc on 2017/7/3.
 */
public class JsonpResult {
    private RESULT result;
    private String result_msg;
    private String faild_reason;
    private Object resultData;

    public static JsonpResult getInstance(){
        return new JsonpResult();
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public RESULT getResult() {
        return result;
    }

    public void setResult(RESULT result) {
        this.result = result;
    }

    public String getFaild_reason() {
        return faild_reason;
    }

    public void setFaild_reason(String faild_reason) {
        this.faild_reason = faild_reason;
    }

    public enum RESULT{
        SUCCESS,
        FAILD
    }
}


