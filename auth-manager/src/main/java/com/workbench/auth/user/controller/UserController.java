package com.workbench.auth.user.controller;

import com.webapp.support.JsonpSupport;
import com.webapp.support.SessionSupport;
import com.webapp.support.jsonp.AuthResult;
import com.workbench.auth.menu.entity.Menu;
import com.workbench.auth.user.service.UserService;
import com.workbench.auth.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
        AuthResult authResult = AuthResult.getInstance();
        if(user!=null){
            List<Menu> allMenu = userService.getMenuList4User(user.getUser_name());
            authResult.setResult(AuthResult.RESULT.SUCCESS);
            authResult.setResult_msg("获取菜单数据成功");
            authResult.setResultData(allMenu);
        }else{
            authResult.setResult(AuthResult.RESULT.FAILD);
            authResult.setFaild_reason("USER_NOT_LOGIN");
            authResult.setResult_msg("用户未登录,请重新登录");
        }
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), authResult);

        logger.debug("get user menu list,result jsonp value :{}",resultJson);

        return resultJson;

    }

}
