package com.o2ocrm.system.service;

import com.o2ocrm.basic.query.PageList;
import com.o2ocrm.system.domain.Employee;
import com.o2ocrm.system.query.EmpQuery;

import java.util.List;

/**
 * @ClassName:IEmployeeService
 * @Description: Employee业务层接口
 * @Author:zfl19
 * @CreateDate:2024/3/26 23:09
 */

public interface IEmployeeService {

    /**
     * 获取所有员工信息
     * @return 所有员工信息集合List
     */
    List<Employee> getAllEmpInfo();

    /**
     * 获取所有员工分页数据
     * @param query 分页参数：页码，每页条数
     * @return 所有员工分页信息集合
     */
    PageList<Employee> getAllEmpInfoByPageList(EmpQuery query);

    /**
     * 根据员工id删除该员工
     * @param id
     */
    void deleteEmpInfoById(Long id);

    /**
     * 根据id集合批量删除员工信息
     * @param query
     */
    void batchDeleteDeptInfoByIds(EmpQuery query);

    /**
     * 新增员工和修改员工信息
     * 通过参数employee的id值来判断是新增还是修改，如果id为空，则为新增，如果id有值，则是修改
     * @param employee
     */
    void insertAndModify(Employee employee);

    /**
     * 获取管理着部门的员工数据
     * @return 返回管理着部门的员工数据
     */
    List<Employee> getHasDeptManagerList();

    /**
     * 获取所有部门经理数据
     * @return
     */
    List<Employee> getEmpHasDeptListBySql();

}
