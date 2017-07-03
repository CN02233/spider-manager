package com.workbench.auth.authvalidate.imp;

import com.AbstractTestService;
import com.workbench.auth.authvalidate.LoginService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by pc on 2017/6/29.
 */
public class LoginServiceImpTest extends AbstractTestService{

    @Resource
    LoginService loginService ;

    @Test
    public void validate(){
        loginService.validate("scq","bb");
    }

}