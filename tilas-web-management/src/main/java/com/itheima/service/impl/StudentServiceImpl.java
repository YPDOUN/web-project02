package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生信息
     *
     * @param studentQueryParam 查询参数
     * @return 分页结果
     */
    @Override
    public PageResult queryStudent(StudentQueryParam studentQueryParam) {

        //1. 获取分页数据
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        //2. 调用mapper层函数查询数据
        List<Student> studentList = studentMapper.queryStudent(studentQueryParam);

        //3.page继承List，强转实现
        Page<Student> list = (Page<Student>) studentList;

        return new PageResult<>(list.getTotal(), list.getResult());
    }

    /**
     * 添加学生信息
     *
     * @param student 学生对象
     */
    @Override
    public void addStudent(Student student) {
        // 补全时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short) 0); // 违规次数默认为0
        student.setViolationScore((short) 0); // 违规分数默认为0

        // 调用mapper层函数插入数据
        studentMapper.addStudent(student);
    }

    /**
     * 删除学生信息
     *
     * @param ids 学生ID列表
     */
    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteStudent(ids);
    }

    /**
     * 根据ID查询学生信息
     *
     * @param id 学生ID
     * @return 学生对象
     */
    @Override
    public Student queryStudentById(Integer id) {
        Student student = studentMapper.queryStudentById(id);
        return student;
    }

    /**
     * 更新学生信息
     *
     * @param student 学生对象
     */
    @Override
    public void updateStudent(Student student) {
        // 更新操作时间
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.updateStudent(student);
    }

    /**
     * 违规处理
     * @param id    学生ID
     * @param score 违规分数
     */
    @Override
    public void violationStudent(Integer id, Integer score) {
        studentMapper.violationStudent(id, score);
    }

}
