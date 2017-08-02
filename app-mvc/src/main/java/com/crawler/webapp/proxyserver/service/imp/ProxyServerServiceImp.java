package com.crawler.webapp.proxyserver.service.imp;

import com.crawler.webapp.proxyserver.bean.ProxyServer;
import com.crawler.webapp.proxyserver.dao.IProxyServerDao;
import com.crawler.webapp.proxyserver.service.ProxyServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SongCQ on 2017/8/2.
 */
@Service("proxyServerService")
public class ProxyServerServiceImp implements ProxyServerService {

    @Autowired
    private IProxyServerDao proxyServerDao;

    @Override
    public List<ProxyServer> listProxyServers() {
        List<ProxyServer> resultServerList = proxyServerDao.listAllProxyServers();
        return resultServerList;
    }
}
