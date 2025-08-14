package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EmpExprMapper {

    void addEmpDetails(List<EmpExpr> exprList);

    void deleteEmpExpr(List<Integer> empExprIds);
}
