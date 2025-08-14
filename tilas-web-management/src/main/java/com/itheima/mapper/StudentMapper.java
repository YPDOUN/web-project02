package com.itheima.mapper;


import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> queryStudent(StudentQueryParam studentQueryParam);

    void addStudent(Student student);

    void deleteStudent(List<Integer> ids);

    Student queryStudentById(Integer id);

    void updateStudent(Student student);

    void violationStudent(Integer id, Integer score);
}
