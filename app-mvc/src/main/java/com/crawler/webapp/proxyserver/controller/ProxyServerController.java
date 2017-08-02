package com.crawler.webapp.proxyserver.controller;

import com.crawler.webapp.proxyserver.bean.ProxyServer;
import com.crawler.webapp.proxyserver.service.ProxyServerService;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.jsonp.JsonpSupport;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by SongCQ on 2017/8/2.
 */
@Controller
@RequestMapping("crawler/proxyServer")
public class ProxyServerController {

    @Autowired
    private ProxyServerService proxyServerService;

    @RequestMapping("listProxyServers")
    @ResponseBody
    @JsonpCallback
    public String listProxyServers(){
        List<ProxyServer> resultData = proxyServerService.listProxyServers();
        String jsonpResult = JsonpSupport.makeJsonpResultStr(JsonResult.RESULT.SUCCESS, "获取成功", null, resultData);
        return jsonpResult;
    }

}
