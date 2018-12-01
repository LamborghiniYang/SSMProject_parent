package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/* @Aspect 切面类的注解
 @Component 被容器管对象
 */
@Aspect
@Component
public class LogUtilAspect {

    //引入日志的业务类用于保存数据
    @Autowired
    private LogService logService;

    @Autowired
    private HttpServletRequest request;

    private SysLog log;

    //定义被增强方法的切入点规则
    @Pointcut("execution( * com.itheima.controller.*.*(..))")
    public void point(){}

    //前置通知
    @Before("point()")
    public void executeBefore(JoinPoint jp){
        //初始化日志对象
        log = new SysLog();
        //当前时间作为访问时间
        log.setVisitTime(new Date());
        //访问者的用户名
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        log.setUsername(user.getUsername());
        //通过request获取ip地址
        log.setIp(request.getRemoteAddr());
        //获取访问的类名和方法
        Class target = jp.getTarget().getClass();
        String simpleName = target.getSimpleName();
        String methodName = jp.getSignature().getName();
        log.setMethod(simpleName+"===="+methodName);

    }
    //后置通知
    @AfterReturning("point()")
    public void executeAfter(){
        log.setExecuteTime(new Date().getTime()-log.getVisitTime().getTime());
        log.setExecuteResult("success");
        log.setExecuteMsg("执行成功");
        logService.saveLog(log);
    }

    //异常通知
    @AfterThrowing(pointcut = "point()",throwing = "e")
    public void executeException(Exception e){
        log.setExecuteTime(new Date().getTime()-log.getVisitTime().getTime());
        log.setExecuteResult("exception");
        log.setExecuteMsg(e.getMessage());
        logService.saveLog(log);
    }

}
