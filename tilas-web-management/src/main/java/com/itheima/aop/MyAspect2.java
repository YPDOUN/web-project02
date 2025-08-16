package com.itheima.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
//@Aspect
public class MyAspect2 {

    /*
     * @Around通知要获取方法执行的相关信息只能使用proceedJointPoint(继承自JoinPoint)
     * 其他四种通知获取方法执行相关的信息使用JoinPoint
     */
    @Before("execution(* com.itheima.service.impl.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("before ...");

        //1. 获取目标对象
        Object object = joinPoint.getTarget();
        log.info("目标对象: {}", object);

        //2. 获取目标类
        String name = joinPoint.getTarget().getClass().getName();
        log.info("目标类: {}", name);

        //3. 获取目标方法
        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法: {}", methodName);

        //4. 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        log.info("目标方法参数: {}", Arrays.toString(args));

    }
}
