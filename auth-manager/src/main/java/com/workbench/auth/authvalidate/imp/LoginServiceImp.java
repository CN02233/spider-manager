package com.workbench.auth.authvalidate.imp;

import com.google.common.base.MoreObjects;
import com.workbench.auth.authvalidate.LoginService;
import com.workbench.auth.authvalidate.bean.LoginResult;
import com.workbench.auth.user.UserService;
import com.workbench.auth.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by pc on 2017/6/29.
 */

@Service("loginService")
public class LoginServiceImp implements LoginService{

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);

    @Autowired
    private UserService userService;

    public LoginResult validate(String userNm, String password) {

        User checkResult = userService.checkUser(userNm, password);

        LoginResult loginResult = new LoginResult();
        if(checkResult!=null){
            loginResult.setResult_code(LoginResult.LOGIN_RESULT.SUCCESS);
            loginResult.setValidate_result("验证通过");
        }else{
            loginResult.setResult_code(LoginResult.LOGIN_RESULT.VALIDATE_FAIL);
            loginResult.setValidate_result("用户名或密码错误");
        }

        String validateResult = MoreObjects.toStringHelper(loginResult).add("result", loginResult.getResult_code()).
                add("result_msg", loginResult.getValidate_result()).toString();

        logger.debug("validate result {}",validateResult);
        return loginResult;
//        return null;
    }
}
