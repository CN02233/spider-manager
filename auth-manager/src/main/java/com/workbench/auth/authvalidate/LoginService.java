package com.workbench.auth.authvalidate;

import com.workbench.auth.authvalidate.bean.LoginResult;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 2017/6/29.
 */
public interface LoginService {

    LoginResult validate(String userNm, String password);

}
