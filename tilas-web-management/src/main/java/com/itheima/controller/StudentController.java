package com.itheima.controller;


import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学生列表查询
     * 分页查询学生
     * @param studentQueryParam 查询参数
     * @return 分页结果
     */
    @GetMapping
    public Result queryStudent(StudentQueryParam studentQueryParam) {
        log.info("前端查询条件: {}", studentQueryParam);

        PageResult studentList = studentService.queryStudent(studentQueryParam);

        return Result.success(studentList);
    }

    /*
     * 删除学生
     * @Param ids 学生ID列表
     * @return 成功结果
     */
    @DeleteMapping("/{ids}")
    public Result deleteStudent(@PathVariable List<Integer> ids) {
        log.info("待删除学生ID列表: {}", ids);

        studentService.deleteStudent(ids);

        return Result.success();
    }

    /**
     * 添加学生信息
     * @param student 请求体json数据
     * @return 成功结果
     */
    @PostMapping
    public Result addStudent(@RequestBody Student student) {
        log.info("新增学生信息: {}", student);

        studentService.addStudent(student);

        return Result.success();
    }

    /**
     * 根据ID查询学生信息
     * @param id 学生ID
     * @return 学生信息
     */
    @GetMapping("/{id}")
    public Result queryStudentById(@PathVariable Integer id) {
        log.info("待查询学生ID: {}", id);

        Student student = studentService.queryStudentById(id);

        return Result.success(student);
    }

    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 成功结果
     */
    @PutMapping
    public Result updateStudent(@RequestBody  Student student) {
        log.info("更新学生信息: {}", student);

        studentService.updateStudent(student);

        return Result.success();
    }

    @PutMapping("violation/{id}/{score}")
    public Result violationStudent(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违规学生ID: {}, 违规分数: {}", id, score);

        studentService.violationStudent(id, score);

        return Result.success();
    }
}
