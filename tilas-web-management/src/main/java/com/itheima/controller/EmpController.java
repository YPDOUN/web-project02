package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * @RequestParam(defaultValue = "value")设置默认值
     * PageResult包含了前端所需的员工总数 + 当前页面能够展示的员工条数
     */
    @GetMapping
    public Result findEmp(EmpQueryParam param) {

        log.info("员工信息: {}", param);

        PageResult pageResult = empService.findEmp(param);
        return Result.success(pageResult);
    }

    /**
     * 添加员工信息
     * RequestBody用于将前端传入的JSON数据转换为Emp对象
     * PostMapping用于处理POST请求
     */
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("员工信息：{}", emp);
        empService.addEmp(emp);

        return Result.success();
    }

    /**
     * 查询所有员工信息
     */
    @GetMapping("/list")
    public Result findAllEmp(){
        log.info("查询所有员工信息");
        List<Emp> list = empService.findAllEmp();
        return Result.success(list);
    }

    /**
     * 删除员工信息
     */
    @DeleteMapping
    public Result deleteEmp(@RequestParam List<Integer> ids) {
        log.info("待删除的员工ID: {}", ids);

        //调用Service层方法进行删除
        empService.deleteEmp(ids);
        return Result.success();
    }

    /**
     * 根据ID查询员工信息
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result findEmpById(@PathVariable Integer id){
        log.info("查询的员工ID: {}", id);

        Emp emp = empService.findEmpById(id);
        return  Result.success(emp);
    }


    /**
     * 更新员工信息
     * @param emp 员工对象
     * @return Result
     */
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp){
        log.info("员工信息：{}", emp);

        //调用Service层方法进行更新
        empService.updateEmp(emp);
        return Result.success();
    }
}
