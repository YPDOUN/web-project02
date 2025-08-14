package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private String name; // 学生姓名
    private Integer degree; // 学历
    private Integer clazzId; // 班级ID
    private Integer page = 1; // 当前页码，默认值为1
    private Integer pageSize = 10; // 每页显示的条数，默认值
}
