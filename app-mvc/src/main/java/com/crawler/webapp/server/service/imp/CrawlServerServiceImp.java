package com.crawler.webapp.server.service.imp;

import com.crawler.webapp.server.bean.CrawlServer;
import com.crawler.webapp.server.dao.ICrawlServerDao;
import com.crawler.webapp.server.service.CrawlServerService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SongCQ on 2017/8/3.
 */
@Service("crawlServerService")
public class CrawlServerServiceImp implements CrawlServerService {

    @Autowired
    private ICrawlServerDao crawlServerDao;

    @Override
    public Page<CrawlServer> pagingServer(int currPage, int pageSize, String host_name) {
        return crawlServerDao.pagingServer(currPage, pageSize, host_name);
    }
}
