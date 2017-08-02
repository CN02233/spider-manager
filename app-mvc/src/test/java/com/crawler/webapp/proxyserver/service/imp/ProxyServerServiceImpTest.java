package com.crawler.webapp.proxyserver.service.imp;

import com.AbstractTestService;
import com.crawler.webapp.proxyserver.bean.ProxyServer;
import com.crawler.webapp.proxyserver.service.ProxyServerService;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by SongCQ on 2017/8/2.
 */
public class ProxyServerServiceImpTest extends AbstractTestService{

    @Resource
    private ProxyServerService proxyServerService;

    @Test
    public void listProxyServers() throws Exception {
        List<ProxyServer> res = proxyServerService.listProxyServers();
        System.out.print("");
    }

}