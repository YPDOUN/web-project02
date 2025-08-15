package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> findEmp(EmpQueryParam param) {

        //1.设置PageHelper的分页参数
        PageHelper.startPage(param.getPage(), param.getPageSize());

        //2.查询获取记录
        List<Emp> rows = empMapper.findEmp(param);

        //3.解析结果并封装
        // Page继承自ArrayList，ArrayList又实现了List接口，因此可以将List强转为Page类型
        Page<Emp> p = (Page<Emp>) rows;

        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void addEmp(Emp emp) {
        try {
            //1.补全属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //2.调用mapper层函数插入数据
            empMapper.addEmp(emp);

            //3.保存员工工作经历信息
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();

            //如果不为空，则遍历exprList，设置每个工作经历的empId
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.addEmpDetails(exprList);
            }
        } finally {
            //finally无论如何都要记录日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public List<Emp> findAllEmp() {
        List<Emp> list = empMapper.findAllEmp();
        return list;
    }

    /**
     * 删除员工信息
     * Transactional注解用于声明事务，rollbackFor指定哪些异常会导致事务回滚
     * 1.删除员工信息
     * 2.删除员工经历信息
     * 3.注意需添加事务注解，防止删除员工信息出现异常时，员工经历信息未被删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmp(List<Integer> ids) {
        //1.删除员工信息
        empMapper.deleteEmp(ids);
        //2.删除员工经历信息
        empExprMapper.deleteEmpExpr(ids);
    }

    /**
     * 根据ID查询员工信息
     * 用于更新员工信息时回显
     * @param id 员工ID
     */
    @Override
    public Emp findEmpById(Integer id) {
        //查询员工信息
        Emp emp = empMapper.findEmpById(id);
        return emp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmp(Emp emp) {
        //1.更新修改时间
        emp.setUpdateTime(LocalDateTime.now());

        //2.更新员工信息
        empMapper.updateEmp(emp);

        //3.删除员工经历信息，调用Array的asList方法将员工的ID转为List数组
        empExprMapper.deleteEmpExpr(Arrays.asList(emp.getId()));

        //4.保存前端传递过来的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //如果前端有传递工作经历，遍历exprList，设置每个工作经历的empId
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.addEmpDetails(exprList);
        }
    }

    /**
     * 登录功能
     * @param emp 员工对象
     * @return 登录信息
     */
    @Override
    public LoginInfo login(Emp emp) {
        //1.查询员工信息
        Emp empInfo = empMapper.findByUsernameAndPassWord(emp.getUsername(), emp.getPassword());
        //2.封装为LoginInfo对象
        if (empInfo != null) {
            //生成 token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empInfo.getId());
            claims.put("username", empInfo.getUsername());

            String token = JwtUtils.generateJwt(claims);
            return new LoginInfo(empInfo.getId(), empInfo.getUsername(), empInfo.getName(), token);
        }

        return null;
     }

}
