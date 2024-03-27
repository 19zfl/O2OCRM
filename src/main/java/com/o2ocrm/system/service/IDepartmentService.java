package com.o2ocrm.system.service;

import com.o2ocrm.basic.query.BaseQuery;
import com.o2ocrm.basic.query.PageList;
import com.o2ocrm.system.domain.Department;

import java.util.List;

/**
 * @ClassName:IDepartmentService
 * @Description: Department业务层接口
 * @Author:zfl19
 * @CreateDate:2024/3/7 21:03
 */

public interface IDepartmentService {

    /**
     * 获取所有部门信息
     * @return 所有部门信息集合List
     */
    List<Department> getAllDeptInfo();

    /**
     * 获取所有部门分页数据
     * @param baseQuery 分页参数：页码，每页条数
     * @return 所有部门分页信息集合
     */
    PageList<Department> getAllDeptInfoByPageList(BaseQuery baseQuery);

    /**
     * 根据部门id删除该部门
     * @param id
     */
    void deleteDeptInfoById(Long id);

    /**
     * 新增部门和修改部门信息
     * 通过参数department的id值来判断是新增还是修改，如果id为空，则为新增，如果id有值，则是修改
     * @param department
     */
    void insertAndModify(Department department);

    /**
     * 获取部门含子集的数据
     * @return 返回部门数据含子集
     */
    List<Department> getTreeDeptList();

    /**
     * 获取父级部门
     * @return 返回父级部门
     */
    List<Department> getParentDeptList();

}
