package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {

    PageResult<Emp> findEmp(EmpQueryParam param);

    void addEmp(Emp emp);

    List<Emp> findAllEmp();

    void deleteEmp(List<Integer> ids);

    Emp findEmpById(Integer id);

    void updateEmp(Emp emp);
}
