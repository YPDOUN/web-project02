package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class clazzQueryParam {
    private Integer page = 1; // 当前页码，默认值为1
    private Integer pageSize = 10; // 每页显示的条数，默认值为10
    private String name; // 班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结束时间
}
