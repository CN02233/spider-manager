package com.webapp.support;

import com.workbench.auth.user.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pc on 2017/7/3.
 */
public class SessionSupport {
    public static User checkoutUserFromSession(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute("user");
        if(userObj!=null)
            return (User) userObj;
        else
            return null;
    }

    public static void addDataToSession(HttpServletRequest request,Object object,String dataName){
        request.getSession().setAttribute(dataName,object);
    }

    public static void addUserToSession(HttpServletRequest request,User user){
        addDataToSession(request,user,"user");
    }
}
