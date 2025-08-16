package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)//用于指定切面的优先级
@Slf4j
@Component
//@Aspect
public class MyAspect1 {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt() {};

    @Before("pt()")
    public void beforeTest(){
        log.info("before ...");
    }

    @Around("pt())")
    public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around before...");

        Object proceed = pjp.proceed();

        log.info("around after...");
        return proceed;
    }

    @After("pt()")
    public void afterTest(){
        log.info("after ...");
    }

    @AfterReturning("pt()")
    public void afterReturningTest() {
        log.info("after returning ...");
    }

    @AfterThrowing("pt()")
    public void afterThrowingTest() {
        log.info("after throwing ...");
    }
}
