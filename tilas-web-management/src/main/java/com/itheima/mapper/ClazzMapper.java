package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.clazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values " +
            "(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    public void addClazz(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> findAllClazz();

    List<Clazz> queryClazz(clazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id = #{id}")
    void deleteClazz(Integer id);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz findClazzById(Integer id);

    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, " +
            "master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    void updateClazz(Clazz clazz);
}
