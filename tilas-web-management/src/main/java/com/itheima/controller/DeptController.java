package com.itheima.controller;


import com.itheima.anno.LogOperation;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger logger = LoggerFactory.getLogger(ClazzController.class);可被替换为注解

    @Autowired
    private DeptService deptService;

    /**
     * 限定请求方式
     * 法一：RequestMethod
     * 法二：RequestMapping的衍生注解：GettingMapping等等 (优先)
     * @return
     */
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result findAllyDept(){
        log.info("查询部门信息");
        List<Dept> list = deptService.findAllDepts();
        return Result.success(list);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @LogOperation
    @DeleteMapping
    public Result deleteDept(@RequestParam("id") Integer id) {
        log.info("删除id为{}部门", id);
        deptService.deleteDept(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @LogOperation
    @PostMapping
    public Result insertDept(@RequestBody Dept dept) {
        log.info("新增：{}", dept.getName());
        deptService.insertDept(dept);
        return Result.success();
    }

    //{id}占位符
    @GetMapping("/{id}")
    //可以直接用@PathVariable Integer id
    public Result findAllDepts(@PathVariable("id") Integer depId) {
        //System.out.println("查询ID：" + depId);
        log.info("查询ID：" + depId);
        Dept deptById = deptService.findDeptById(depId);
        return Result.success(deptById);
    }

    /**
     * 修改部门
     * @param dept
     * @return
     */
    @LogOperation
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        //System.out.println("id: " + dept.getId() + ", name: " + dept.getName());
        log.info("id: {}, name: {}", dept.getId(), dept.getName());
        deptService.updateDept(dept);
        return Result.success();
    }
}