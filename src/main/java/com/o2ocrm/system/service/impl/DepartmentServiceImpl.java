package com.o2ocrm.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.o2ocrm.basic.query.BaseQuery;
import com.o2ocrm.basic.query.PageList;
import com.o2ocrm.system.domain.Department;
import com.o2ocrm.system.mapper.DepartmentMapper;
import com.o2ocrm.system.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:DepartmentServiceImpl
 * @Description: Department业务层接口实现类
 * @Author:zfl19
 * @CreateDate:2024/3/7 23:27
 */

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    /**
     * 注入持久层实现类
     */
    @Resource
    private DepartmentMapper deptMapper;

    /**
     * 获取所有部门信息
     * @return 所有部门信息集合List
     */
    @Override
    public List<Department> getAllDeptInfo() {
        return deptMapper.selectAll();
    }

    /**
     * 获取所有部门分页数据
     * @param baseQuery 分页参数：页码，每页条数
     * @return 所有部门分页信息集合
     */
    @Override
    public PageList<Department> getAllDeptInfoByPageList(BaseQuery baseQuery) {
        // 设置分页参数
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        // 执行查询操作
        List<Department> deptList = deptMapper.selectAll();
        // 封装分页数据
        PageInfo<Department> deptListByPageInfo = new PageInfo<>(deptList);
        // 将分页数据封装进PageList中
        PageList<Department> deptListByPageList = new PageList<>(deptListByPageInfo.getTotal(), deptListByPageInfo.getList());
        // 返回数据
        return deptListByPageList;
    }

    /**
     * 根据部门id删除该部门
     * @param id
     */
    @Override
    public void deleteDeptInfoById(Long id) {
        deptMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增部门和修改部门信息
     * 通过参数department的id值来判断是新增还是修改，如果id为空，则为新增，如果id有值，则是修改
     * @param department
     */
    @Override
    public void insertAndModify(Department department) {
        // 判断department中id是否为空值
        if (department.getId() == null ) {
            // 为空：新增操作
            deptMapper.insertSelective(department);
        } else {
            // 不为空：修改操作
            deptMapper.updateByPrimaryKeySelective(department);
        }
    }
}
