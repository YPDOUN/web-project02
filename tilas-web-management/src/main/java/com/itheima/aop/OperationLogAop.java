package com.itheima.aop;


import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAop {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.LogOperation)")
    public Object logRecord(ProceedingJoinPoint pjp) throws Throwable {
        //获取操作时间
        Long begin = System.currentTimeMillis();
        //执行方法
        Object result = pjp.proceed();
        //获取方法执行时间
        Long end = System.currentTimeMillis();
        long costTime = end - begin;


        OperateLog olog = new OperateLog();
        //设置操作时间
        olog.setOperateTime(LocalDateTime.now());
        //设置全类名
        olog.setClassName(pjp.getTarget().getClass().getName());
        //设置执行方法名称
        olog.setMethodName(pjp.getSignature().getName());
        //设置方法参数
        Object[] paramList = pjp.getArgs();
        olog.setMethodParams(Arrays.toString(paramList));
        //设置返回值
        olog.setReturnValue(result == null ? "void" : result.toString());
        //设置执行时间
        olog.setCostTime(costTime);
        //设置操作员工ID
        olog.setOperateEmpId(getEmpId());

        //输出日志
        log.info("操作日志：{}", olog);

        //插入日志
        operateLogMapper.insert(olog);

        return result;
    }

    //获取当前操作员工号
    private Integer getEmpId() {
        return CurrentHolder.getCurrentId();
    }
}
