package com.workbench.spring.aop.clazz;

import com.google.common.base.Strings;
import com.webapp.support.jsonp.JsonpSupport;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SongCQ on 2017/7/25.
 * JSONP报文AOP类
 */
@Component
@Aspect
public class JsonpAspect {

    private Logger logger = LoggerFactory.getLogger(JsonpAspect.class);

    @Pointcut("@annotation(com.workbench.spring.aop.annotation.JsonpCallback)")
    private void annotationJsonpCallback(){} //声明一个切入点,切入点的名称其实是一个方法


    @Around("annotationJsonpCallback()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
        Object object=pjp.proceed();
        String jsonpCallBackStr = JsonpSupport.objectToJsonp(getJsonpCallbackName(), object);
        logger.debug("Response message to web page value is: {}",jsonpCallBackStr);
        return jsonpCallBackStr;
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
