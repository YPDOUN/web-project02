package com.itheima.controller;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.clazzQueryParam;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 添加班级
     * @param clazz 班级信息
     * @return 成功结果
     */
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        clazzService.addClazz(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级
     * @return 班级列表
     */
    @GetMapping("/list")
    public Result findAllClazz() {
        List<Clazz> list = clazzService.findAllClazz();
        return Result.success(list);
    }

    /**
     * 班级列表查询
     * 分页查询班级
     * @param clazzQueryParam 查询参数
     * @return 分页结果
     */
    @GetMapping
    public Result queryClazz(clazzQueryParam clazzQueryParam) {
        log.info("查询条件: {}", clazzQueryParam);

        //交给Service层封装为PageResult
        PageResult pageResult = clazzService.queryClazz(clazzQueryParam);
        return Result.success(pageResult);

    }

    /**
     * 删除班级
     * @param id 班级ID
     * @return 成功结果
     */
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("待删除Id：{}", id);

        clazzService.deleteClazz(id);
        return Result.success();
    }

    /**
     * 根据ID查询班级
     * @param id 班级ID
     * @return 班级信息
     */
    @GetMapping("/{id}")
    public Result findClazzById(@PathVariable Integer id){
        log.info("查找Id：{}", id);

        Clazz clazz = clazzService.findClazzById(id);
        return Result.success(clazz);
    }

    /**
     * 更新班级信息
     * @param clazz 班级信息
     * @return 成功结果
     */
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("待修改班级信息：{}", clazz);

        clazzService.updateClazz(clazz);
        return Result.success();
    }
}
