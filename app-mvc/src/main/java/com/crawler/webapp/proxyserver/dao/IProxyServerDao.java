package com.crawler.webapp.proxyserver.dao;

import com.crawler.webapp.proxyserver.bean.ProxyServer;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by SongCQ on 2017/8/2.
 */
public interface IProxyServerDao {

    @Select("select * from poxy_server")
    @Options(useCache = false)
    List<ProxyServer> listAllProxyServers();
}
