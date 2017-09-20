package com.workbench.spring.aop.clazz;

import com.google.common.base.Strings;
import com.webapp.support.json.JsonSupport;
import com.webapp.support.jsonp.JsonResult;
import com.webapp.support.jsonp.JsonpSupport;
import com.webapp.support.session.SessionSupport;
import com.workbench.auth.authvalidate.controller.LoginController;
import com.workbench.auth.user.entity.User;
import com.workbench.exception.runtime.NotLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SongCQ on 2017/7/25.
 */

@Aspect
@Component
@Order(1)
public class SystemLogAspect {
    private Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //expression="execution(* package1.*.*(..)) || execution(* package2.*.*(..))"
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void allMethod(){}

    @Around("allMethod()")
    public Object doAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object targer = joinPoint.getTarget();
            if(!(targer instanceof LoginController)){
                User user = SessionSupport.checkoutUserFromSession();
                Object[] allArgs = joinPoint.getArgs();
                logger.info("User:-->{}<-- called method:-->{}<--,the param values-->{}<--",user,joinPoint.toString(),allArgs);
            }
            return joinPoint.proceed();
        }catch(Exception e){
            e.printStackTrace();

            if(e instanceof NotLoginException){
                String jsonpCallBackStr = JsonpSupport.objectToJsonp(getJsonpCallbackName(),
                        JsonSupport.makeJsonResultStr(JsonResult.RESULT.FAILD, "用户未登录", "USER_NOT_LOGIN", null));
                return jsonpCallBackStr;
            }
            String jsonpCallBackStr = JsonpSupport.objectToJsonp(getJsonpCallbackName(),
                    JsonSupport.makeJsonResultStr(JsonResult.RESULT.FAILD, "系统异常", "异常原因:" + e.toString(), null));

            return jsonpCallBackStr;
        }
    }

    private String getJsonpCallbackName(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String callBackFunctionName = JsonpSupport.jsonpCallbackFunctionName(request);
        callBackFunctionName = Strings.isNullOrEmpty(callBackFunctionName)?"callback":callBackFunctionName;
        logger.debug("call back function name is {}",callBackFunctionName);
        return callBackFunctionName;
    }
}
