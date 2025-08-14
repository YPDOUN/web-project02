package com.itheima.mapper;


import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    public List<Dept> findAllDepts();

    @Delete("delete from dept where id = #{id}")
    public void deleteDept(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    public void insertDept(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    public Dept findDeptById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void updateDept(Dept dept);
}
