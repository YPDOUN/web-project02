package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.clazzQueryParam;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public void addClazz(Clazz clazz) {
        //补全属性
        //设置创建时间和最后更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.addClazz(clazz);
    }

    @Override
    public List<Clazz> findAllClazz() {
        return clazzMapper.findAllClazz();
    }

    @Override
    public PageResult queryClazz(clazzQueryParam clazzQueryParam) {
        //1. 获取分页数据
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        //2.获取total
        List<Clazz> list = clazzMapper.queryClazz(clazzQueryParam);

        //3.补充开课状态
        LocalDate now = LocalDate.now();
        for (Clazz clazz : list) {
            //获取开课时间和结课时间
            LocalDate begin = clazz.getBeginDate();
            LocalDate end = clazz.getEndDate();

            if(now.isBefore(begin)){
                clazz.setStatus("未开班");
            } else if(now.isAfter(end)){
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("已开班");
            }
        }

        //4.转换类型
        Page<Clazz> pageResult = (Page<Clazz>)list;

        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);
    }

    @Override
    public Clazz findClazzById(Integer id) {
        Clazz clazz = clazzMapper.findClazzById(id);

        return clazz;
    }

    @Override
    public void updateClazz(Clazz clazz) {
        //更新操作时间
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.updateClazz(clazz);
    }
}
