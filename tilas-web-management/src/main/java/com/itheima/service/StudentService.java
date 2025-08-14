package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult queryStudent(StudentQueryParam studentQueryParam);

    void addStudent(Student student);

    void deleteStudent(List<Integer> ids);

    Student queryStudentById(Integer id);

    void updateStudent(Student student);

    void violationStudent(Integer id, Integer score);
}
