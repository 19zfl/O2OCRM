package com.o2ocrm.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.o2ocrm.basic.query.BaseQuery;
import com.o2ocrm.basic.query.PageList;
import com.o2ocrm.system.domain.Employee;
import com.o2ocrm.system.mapper.EmployeeMapper;
import com.o2ocrm.system.service.IEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:EmployeeServiceImpl
 * @Description: Employee业务层接口实现类
 * @Author:zfl19
 * @CreateDate:2024/3/26 23:12
 */

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    /** 注入持久层实现类 */
    @Resource
    private EmployeeMapper empMapper;

    /**
     * 获取所有员工信息
     * @return
     */
    @Override
    public List<Employee> getAllEmpInfo() {
        return empMapper.selectAll();
    }

    /**
     * 获取所有员工分页数据
     * @param baseQuery 分页参数：页码，每页条数
     * @return 所有员工分页信息集合
     */
    @Override
    public PageList<Employee> getAllEmpInfoByPageList(BaseQuery baseQuery) {
        // 设置分页参数
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        // 执行查询操作
        List<Employee> empList = empMapper.selectAll();
        // 封装分页数据
        PageInfo<Employee> empListByPageInfo = new PageInfo<>(empList);
        // 将分页数据封装进PageList中
        PageList<Employee> empListByPageList = new PageList<>(empListByPageInfo.getTotal(), empListByPageInfo.getList());
        // 返回数据
        return empListByPageList;
    }

    /**
     * 根据员工id删除该员工
     * @param id
     */
    @Override
    public void deleteEmpInfoById(Long id) {
        empMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增员工和修改员工信息
     * 通过参数employee的id值来判断是新增还是修改，如果id为空，则为新增，如果id有值，则是修改
     * @param employee
     */
    @Override
    public void insertAndModify(Employee employee) {
        // 判断employee中id是否为空值
        if (employee.getId() == null) {
            // 为空：新增操作
            empMapper.insertSelective(employee);
        } else {
            // 不为空：修改操作
            empMapper.updateByPrimaryKeySelective(employee);
        }
    }
}
