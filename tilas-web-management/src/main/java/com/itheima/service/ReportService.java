package com.itheima.service;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentCountOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    List<Map> getStudentDegreeData();

    StudentCountOption getStudentCountData();
}
