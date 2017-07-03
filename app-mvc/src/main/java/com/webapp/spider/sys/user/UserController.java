package com.webapp.spider.sys.user;

import com.webapp.spider.sys.JsonpResult;
import com.webapp.support.JsonpSupport;
import com.webapp.support.SessionSupport;
import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.menu.service.MenuService;
import com.workbench.auth.user.UserService;
import com.workbench.auth.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by pc on 2017/6/30.
 */
@Controller
@RequestMapping("sys/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("listUserPage")
    @ResponseBody
    public List<User> getUserByPage(int currPage,int pageSize){
        List<User> userPageList = userService.listUsersForPage(currPage, pageSize);
        return userPageList;
    }

    @RequestMapping("userMenuList")
    @ResponseBody
    public String getMenuList4User(HttpServletRequest request){
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            logger.debug("header name is -->{}<-- and header value is -->{}<--",headerName,request.getHeader(headerName));
//        }

        User user = SessionSupport.checkoutUserFromSession(request);
        JsonpResult jsonpResult = JsonpResult.getInstance();
        if(user!=null){
            List<Menu> allMenu = menuService.getMenuList4User(user.getUser_name());
            jsonpResult.setResult(JsonpResult.RESULT.SUCCESS);
            jsonpResult.setResult_msg("获取菜单数据成功");
            jsonpResult.setResultData(allMenu);
        }else{
            jsonpResult.setResult(JsonpResult.RESULT.FAILD);
            jsonpResult.setFaild_reason("USER_NOT_LOGIN");
            jsonpResult.setResult_msg("用户未登录,请重新登录");
        }
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonpResult);

        logger.debug("get user menu list,result jsonp value :{}",resultJson);

        return resultJson;

    }

}
