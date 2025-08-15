package com.itheima.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class RecordTimeAspect {

    //添加执行路径
    @Around("execution(* com.itheima.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {

        //1.记录开始时间
        long begin = System.currentTimeMillis();

        //2.调用方法
        Object result = pjp.proceed();

        //3.记录结束时间，并计算耗时
        long end = System.currentTimeMillis();

        String string = pjp.getSignature().toString();
        String implClass = string.substring(string.indexOf("impl")).split("\\.")[2];

        log.info(string);
        

        log.info("方法{}执行耗时：{}ms", implClass, end - begin);
        return result;
    }
}
