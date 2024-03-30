package com.o2ocrm.system.mapper;

import com.o2ocrm.system.domain.Employee;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName:EmployeeMapper
 * @Description: Employee持久层
 * @Author:zfl19
 * @CreateDate:2024/3/26 23:08
 */

public interface EmployeeMapper extends Mapper<Employee> {

    /**
     * 基本的CRUD通过继承tk提供的Mapper接口获得
     */

    /**
     * 获取所有部门经理数据
     * @return 返回数据集合
     * @error sql正确却获取到了sql之外的数据
     * TODO 目前使用的遍历方式获取而非sql直接获取，遍历方式造成了查询了数据库两次
     */
    List<Employee> getEmpHasDeptListBySql();

}
