package com.workbench.auth.user.controller;

import com.github.pagehelper.Page;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.session.SessionSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.page.PageResult;
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
    public String getUserByPage(int currPage, int pageSize,HttpServletRequest request){
        Page<User> userPageList = userService.listUsersForPage(currPage, pageSize);
        PageResult pageResult = PageResult.pageHelperList2PageResult(userPageList);
        String result = JsonpSupport.makeJsonpResponse(JsonResult.RESULT.SUCCESS,"获取成功",null,pageResult,request);
        return result;
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
        JsonResult jsonResult = JsonResult.getInstance();
        if(user!=null){
            List<Menu> allMenu = userService.getMenuList4User(user.getUser_name());
            jsonResult.setResult(JsonResult.RESULT.SUCCESS);
            jsonResult.setResult_msg("获取菜单数据成功");
            jsonResult.setResultData(allMenu);
        }else{
            jsonResult.setResult(JsonResult.RESULT.FAILD);
            jsonResult.setFaild_reason("USER_NOT_LOGIN");
            jsonResult.setResult_msg("用户未登录,请重新登录");
        }
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonResult);

        logger.debug("get user menu list,result jsonp value :{}",resultJson);

        return resultJson;

    }


    @RequestMapping("saveNewUser")
    @ResponseBody
    public String saveNewUser(User user,HttpServletRequest request){
        userService.createUser(user);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(JsonResult.RESULT.SUCCESS);
        jsonResult.setResult_msg("保存成功");
        logger.debug("user bean information after create :{}, and json value is 【{}】",user.toString(),jsonResult.toString());
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonResult);

        return resultJson;
    }

    @RequestMapping("delUserByUserId")
    @ResponseBody
    public String delUserByUserId(Integer user_id,HttpServletRequest request){
        userService.delUserById(user_id);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(JsonResult.RESULT.SUCCESS);
        jsonResult.setResult_msg("删除成功");
        logger.debug("jsonResult information after delete :{}",jsonResult.toString());
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonResult);

        return resultJson;
    }

    @RequestMapping("getUserByUserId")
    @ResponseBody
    public String getUserByUserId(Integer user_id,HttpServletRequest request){
        User user = userService.getUserByUserId(user_id);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(JsonResult.RESULT.SUCCESS);
        jsonResult.setResult_msg("获取成功");
        jsonResult.setResultData(user);
        logger.debug("jsonResult information after delete :{}",jsonResult.toString());
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonResult);

        return resultJson;
    }

    @RequestMapping("updateSaveUser")
    @ResponseBody
    public String updateSaveUser(User user,HttpServletRequest request){
        userService.updateUser(user);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setResult(JsonResult.RESULT.SUCCESS);
        jsonResult.setResult_msg("保存成功");
        logger.debug("jsonResult information after delete :{}",jsonResult.toString());
        String resultJson = JsonpSupport.objectToJsonp(JsonpSupport.jsonpCallbackFunctionName(request), jsonResult);

        return resultJson;
    }
}
