package com.webapp.spider.sys.login;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;
import com.webapp.support.JsonpSupport;
import com.webapp.support.SessionSupport;
import com.workbench.auth.authvalidate.LoginService;
import com.workbench.auth.authvalidate.bean.LoginResult;
import com.workbench.auth.user.UserService;
import com.workbench.auth.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import java.util.Enumeration;

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
    public String doLogin(String user_name, String user_pwd, String web_call_back, HttpServletRequest request){

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            logger.debug("header name is -->{}<-- and header value is -->{}<--",headerName,request.getHeader(headerName));
//        }


        boolean checkResult = Strings.isNullOrEmpty(user_name);
        LoginResult validateResult = null;
        if(checkResult){
            validateResult = new LoginResult();
            validateResult.setValidate_result("用户名为空");
            validateResult.setResult_code(LoginResult.LOGIN_RESULT.USERNM_NOT_NULL);
        }else{
            validateResult = loginService.validate(user_name, user_pwd);
            SessionSupport.addUserToSession(request,userService.getUserByUserNm(user_name));
        }

        String jsonpMsg = JsonpSupport.objectToJsonp(web_call_back, validateResult);
        logger.debug("login validate result jsonp message -->{}",jsonpMsg);
        return jsonpMsg;
    }

}
