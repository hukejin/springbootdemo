package com.liu.springbootdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录切面
 */
@Aspect
@Component
public class LoginHandler {

    private final static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Pointcut("execution(public * com.liu.springbootdemo.controller..*.*(..)) && @annotation(com.liu.springbootdemo.aop.LoginCheck)")
    public void loginCheck(){

    }

    @Before("loginCheck()")
    public ModelAndView toLogin(JoinPoint joinPoint){

        logger.info("it`s does!");

        return new ModelAndView("");
    }
}
