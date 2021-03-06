package com.workbench.auth.authvalidate.controller;

import com.google.common.base.Strings;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.session.SessionSupport;
import com.workbench.auth.authvalidate.service.LoginService;
import com.workbench.auth.authvalidate.bean.LoginResult;
import com.workbench.auth.user.entity.User;
import com.workbench.auth.user.service.UserService;
import com.workbench.spring.aop.annotation.JsonpCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pc on 2017/6/30.
 */

@Controller
@RequestMapping("sys/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping("doLogin")
    @ResponseBody
    @JsonpCallback
    public String doLogin(String user_name, String user_pwd){
        boolean checkResult = Strings.isNullOrEmpty(user_name);
        if(checkResult){
            return JsonSupport.makeJsonResultStr(JsonResult.RESULT.FAILD, "用户名为空", LoginResult.LOGIN_RESULT.USERNM_NOT_NULL.toString(), null);

        }else{
            loginService.validate(user_name, user_pwd);
            User user = userService.getUserByUserNm(user_name);
            if(user==null){
                return JsonSupport.makeJsonResultStr(JsonResult.RESULT.FAILD, "当前登录/操作的用户不存在",
                        LoginResult.LOGIN_RESULT.USERNM_NOT_FOUND.toString(), null);
            }else
                SessionSupport.addUserToSession(userService.getUserByUserNm(user_name));
        }
        return JsonSupport.makeJsonResultStr(JsonResult.RESULT.SUCCESS, "登录成功",null, null);
    }

}
