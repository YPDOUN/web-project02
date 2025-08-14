package com.itheima.service.impl;

import com.itheima.Exception.DepartmentNotEmptyException;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    public List<Dept> findAllDepts() {
        return deptMapper.findAllDepts();
    }


    public void deleteDept(Integer id) {
        //删除部门之前，先判断该部门下是否有员工
        Integer count = empMapper.findEmpByDeptId(id);
        if (Objects.nonNull(count) && count > 0) {
            //如果有员工，则抛出异常
            throw new DepartmentNotEmptyException();
        }

        deptMapper.deleteDept(id);
    }

    public void insertDept(Dept dept) {
        /**
         * 补全属性
         */
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper层函数插入数据
        deptMapper.insertDept(dept);
    }

    @Override
    public Dept findDeptById(Integer id) {
        return deptMapper.findDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        /**
         * 更新update时间
         */
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.updateDept(dept);
    }
}
