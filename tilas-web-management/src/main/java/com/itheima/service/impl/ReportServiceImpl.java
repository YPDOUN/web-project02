package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentCountOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    /*
     * 统计员工职位信息
     */
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> empJobData = empMapper.getEmpJobData();

        List<Object> jobList = empJobData.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = empJobData.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    /*
     * 统计员工性别信息
     */
    @Override
    public List<Map> getEmpGenderData() {
        List<Map> genderOptions = empMapper.getEmpGenderData();

        return genderOptions;
    }

    @Override
    public List<Map> getStudentDegreeData() {
        List<Map> degreeOptions = empMapper.getStudentDegreeData();

        return degreeOptions;
    }

    @Override
    public StudentCountOption getStudentCountData() {
        List<Map> studentCountList = empMapper.getStudentCountData();

        List clazzList = studentCountList.stream().map(dataMap -> dataMap.get("name")).toList();
        List dataList = studentCountList.stream().map(dataMap -> dataMap.get("count")).toList();

        return new StudentCountOption(clazzList, dataList);
    }

}
