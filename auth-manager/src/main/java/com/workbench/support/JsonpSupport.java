package com.webapp.support;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pc on 2017/7/3.
 */
public class JsonpSupport {

    public static String objectToJsonp(String jsonp_callback,Object object){
        Preconditions.checkNotNull(object);
        jsonp_callback = Strings.isNullOrEmpty(jsonp_callback)?"callback":jsonp_callback;
        Gson gson = new Gson();


        return new StringBuilder().append(jsonp_callback).append("(").append(gson.toJson(object)).append(")").toString();
    }

    public static String jsonpCallbackFunctionName(HttpServletRequest request){
        String web_call_back = request.getParameter("web_call_back");
        if(Strings.isNullOrEmpty(web_call_back)){
            web_call_back = (String) request.getAttribute("web_call_back");
        }
        return web_call_back;
    }

}
