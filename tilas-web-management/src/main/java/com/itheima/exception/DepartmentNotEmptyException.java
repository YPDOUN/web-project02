package com.itheima.exception;

public class DepartmentNotEmptyException extends RuntimeException {

    public DepartmentNotEmptyException() {
        super("该部门下存在员工，无法删除！");
    }
}
