package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    List<Emp> findEmp(EmpQueryParam param);

    /**
     * 添加员工信息
     * @param emp 员工对象
     * useGeneratedKeys = true表示使用自动生成的主键
     * keyProperty = "id"表示将自动生成的主键值设置到emp
     * 获取到的主键值会被设置到emp对象的id属性中，id是数据库自增
     * 可以直接拿emp的id属性来插入员工经历
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image},  #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void addEmp(Emp emp);

    /**
     * 查询所有员工信息
     * @return 员工列表
     */
    @Select("select * from emp")
    List<Emp> findAllEmp();

    void deleteEmp(List<Integer> empIds);

    Emp findEmpById(Integer id);

    void updateEmp(Emp emp);

    List<Map<String, Object>> getEmpJobData();


    List<Map> getEmpGenderData();

    Integer findEmpByDeptId(Integer id);

    List<Map> getStudentDegreeData();

    List<Map> getStudentCountData();

    @Select("select id, username, name from emp where username = #{username} and password = #{password}")
    Emp findByUsernameAndPassWord(String username, String password);
}

