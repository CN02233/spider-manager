package com.workbench.spring.aop.clazz;

import com.workbench.auth.user.entity.User;
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
 */

@Aspect
@Component
public class SystemLogAspect {
    private Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //expression="execution(* package1.*.*(..)) || execution(* package2.*.*(..))"
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void allMethod(){}

    @Around("allMethod()")
    public Object doAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("user");
        Object[] allArgs = joinPoint.getArgs();
        logger.info("User:-->{}<-- called method:-->{}<--,the param values-->{}<--",user,joinPoint.toString(),allArgs);
        Object resultObj = joinPoint.proceed();
        return resultObj;
    }
}
