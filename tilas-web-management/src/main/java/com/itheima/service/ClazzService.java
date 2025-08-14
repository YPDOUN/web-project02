package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.clazzQueryParam;

import java.util.List;

public interface ClazzService {

    public void addClazz(Clazz clazz);

    List<Clazz> findAllClazz();

    PageResult queryClazz(clazzQueryParam clazzQueryParam);

    void deleteClazz(Integer id);

    Clazz findClazzById(Integer id);

    void updateClazz(Clazz clazz);
}
