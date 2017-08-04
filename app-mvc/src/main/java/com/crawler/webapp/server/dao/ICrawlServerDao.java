package com.crawler.webapp.server.dao;

import com.crawler.webapp.server.bean.CrawlServer;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by SongCQ on 2017/8/3.
 */
public interface ICrawlServerDao {

    @Select("select * from crawl_host " +
            "<where>" +
            "<if test='host_name !=null and host_name!=&quot;&quot;'> " +
            "   host_name concat('%',#{host_name},'%') " +
            "</if>" +
            "</where>")
    Page<CrawlServer> pagingServer(@Param("currPage") int currPage,@Param("pageSize") int pageSize,
                                   @Param("host_name") String host_name);

}
