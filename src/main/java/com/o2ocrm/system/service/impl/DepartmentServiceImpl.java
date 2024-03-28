package com.o2ocrm.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.o2ocrm.basic.query.BaseQuery;
import com.o2ocrm.basic.query.PageList;
import com.o2ocrm.system.domain.Department;
import com.o2ocrm.system.mapper.DepartmentMapper;
import com.o2ocrm.system.service.IDepartmentService;
import com.o2ocrm.system.utils.EditParentFieldUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Department> deptList = deptMapper.getAllDeptListBySql();
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
        // 如果时父级部门，那么删除的同时子级部门也要同时进行删除，避免产生脏数据
        // 获取所有部门数据，只要dir_path属性中有此id一律删除掉
        List<Department> deptList = deptMapper.selectAll();
        for (Department dept : deptList) {
            if (dept.getDirPath().contains(String.valueOf(id)))
                deptMapper.deleteByPrimaryKey(dept.getId());
        }
    }

    /**
     * 根据id数组进行批量删除
     * @param baseQuery id数组
     */
    @Override
    public void batchDeleteDeptInfoByIds(BaseQuery baseQuery) {
        // 如果时父级部门，那么删除的同时子级部门也要同时进行删除，避免产生脏数据
        // 获取所有部门数据，只要dir_path属性中有此id一律删除掉
        List<Department> deptList = deptMapper.selectAll();
        for (Long id : baseQuery.getIds()) {
            for (Department dept : deptList) {
                if (dept.getDirPath().contains(String.valueOf(id)))
                    deptMapper.deleteByPrimaryKey(dept.getId());
            }
        }
    }

    /**
     * 新增部门和修改部门信息
     * 通过参数department的id值来判断是新增还是修改，如果id为空，则为新增，如果id有值，则是修改
     * @param dept
     */
    @Override
    public void insertAndModify(Department dept) {
        if (dept.getId() == null) {
            deptMapper.insertSelective(dept);
        }
        EditParentFieldUtil.updateParentAndPath(dept);
        deptMapper.updateByPrimaryKeySelective(dept);
    }

    /**
     * 获取部门含子集的数据
     * @return 返回部门数据含子集
     */
    @Override
    public List<Department> getTreeDeptList() {
        // 获取所有部门数据
        List<Department> deptList = deptMapper.selectAll();
        // 创建一个空集合存放数据最后返回给前端
        List<Department> treeDeptList = new ArrayList<>();
        // 创建一个集合存放遍历数据
        Map<Long, Department> map = new HashMap<>();
        for (Department dept : deptList) {
            map.put(dept.getId(), dept);
        }
        // 遍历部门数据
        for (Department dept : deptList) {
            // 判断是否有父级id
            if (dept.getParentId() == null) {
                // 没有，说明是顶级
                treeDeptList.add(dept);
            } else {
                // 有，将自己存入父级对象的子集中
                // 通过子级部门的parentId拿到父级部门对象
                Department parentDept = map.get(dept.getParentId());
                // 将自己存入父级对象中
                parentDept.getChildren().add(dept);
            }
        }
        return treeDeptList;
    }

    /**
     * 获取父级部门
     * @return 返回父级部门
     */
    @Override
    public List<Department> getParentDeptList() {
        // 获取所有部门数据
        List<Department> deptList = deptMapper.selectAll();
        // 创建空集合存放父级部门数据
        List<Department> parentList = new ArrayList<>();
        // 遍历取出父级部门
        for (Department dept : deptList) {
            if (dept.getParentId() == null && StringUtils.isEmpty(dept.getParentId())) {
                parentList.add(dept);
            }
        }
        return parentList;
    }
}
