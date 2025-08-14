package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    public List<Dept> findAllDepts();

    public void deleteDept(Integer id);

    public void insertDept(Dept dept);

    public Dept findDeptById(Integer id);

    public void updateDept(Dept dept);
}
