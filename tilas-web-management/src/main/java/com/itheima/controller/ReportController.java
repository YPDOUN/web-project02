package com.itheima.controller;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.pojo.StudentCountOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 员工职位统计
     * @return 返回职位统计数据
     */
    @GetMapping("/empJobData")
    public Result empJobDateReport() {
        JobOption jobOption = reportService.getEmpJobData();

        return Result.success(jobOption);
    }

    /**
     * 员工性别统计
     * @return 返回性别统计数据
     */
    @GetMapping("/empGenderData")
    public Result empGenderDataReport() {
        List<Map> genderOptions = reportService.getEmpGenderData();

        return Result.success(genderOptions);
    }

    /**
     * 学生学历统计
     * @return 返回学生学历统计数据
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeDataReport() {
        List<Map> degreeOptions = reportService.getStudentDegreeData();

        return Result.success(degreeOptions);
    }

    /**
     * 学生数量统计
     * @return 返回学生数量统计数据
     */
    @GetMapping("/studentCountData")
    public Result studentCountDataReport() {
        StudentCountOption studentCountOption = reportService.getStudentCountData();
        return Result.success(studentCountOption);
    }
}
