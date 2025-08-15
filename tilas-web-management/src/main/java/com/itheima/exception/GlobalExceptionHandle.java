package com.itheima.exception;


import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.error("系统出错！请联系管理员！");
    }

    @ExceptionHandler
    public Result handleException(DuplicateKeyException e) {
        log.error("数据库数据重复异常：", e);

        String message = e.getMessage();
        String keyString = message.substring(message.indexOf("Duplicate entry"));
        String duplicatePart = keyString.split(" ")[2];

        return Result.error(duplicatePart + "已存在，请勿重复添加！");
    }

    @ExceptionHandler
    public Result handleException(DepartmentNotEmptyException d) {
        log.error("部门存在员工异常：", d);

        return Result.error(d.getMessage());
    }

}
