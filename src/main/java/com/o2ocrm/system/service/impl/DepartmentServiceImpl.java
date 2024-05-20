package com.o2ocrm.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.o2ocrm.basic.query.PageList;
import com.o2ocrm.basic.utils.redis.RedisService;
import com.o2ocrm.system.domain.Department;
import com.o2ocrm.system.mapper.DepartmentMapper;
import com.o2ocrm.system.query.DeptQuery;
import com.o2ocrm.system.service.IDepartmentService;
import com.o2ocrm.system.utils.dept.EditParentFieldUtil;
import com.o2ocrm.system.utils.dept.TreeListByDept;
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
     * 注入操作Redis缓存Bean依赖
     */
    @Resource
    private RedisService redisService;

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
     * @param query 分页参数：页码，每页条数
     * @return 所有部门分页信息集合
     */
    @Override
    public PageList<Department> getAllDeptInfoByPageList(DeptQuery query) {
        // 设置分页参数
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        // 执行查询操作
        List<Department> deptList = deptMapper.getAllDeptListBySql(query);
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
        // 重建缓存
        redisService.deleteRedisByKey("treeDept");
    }

    /**
     * 根据id数组进行批量删除
     * @param query id数组
     */
    @Override
    public void batchDeleteDeptInfoByIds(DeptQuery query) {
        // 如果时父级部门，那么删除的同时子级部门也要同时进行删除，避免产生脏数据
        // 获取所有部门数据，只要dir_path属性中有此id一律删除掉
        List<Department> deptList = deptMapper.selectAll();
        for (Long id : query.getIds()) {
            for (Department dept : deptList) {
                if (dept.getDirPath().contains(String.valueOf(id)))
                    deptMapper.deleteByPrimaryKey(dept.getId());
            }
        }
        // 重建缓存
        redisService.deleteRedisByKey("treeDept");
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
        // 重建缓存
        redisService.deleteRedisByKey("treeDept");
    }

    /**
     * 获取部门含子集的数据
     * @return 返回部门数据含子集
     */
    @Override
    public List<Department> getTreeDeptList() {
        // 默认树形数据在redis中的key为deptTree
        // 1.从redis中获取key为deptTree的数据
        String data = redisService.getKeyObjectValue("deptTree", String.class);
        // 2.判断是否有值
        if (StrUtil.isEmpty(data)) {
            // 没有
            // 获取树形数据
            List<Department> deptList = deptMapper.selectAll();
            List<Department> deptTreeList = TreeListByDept.getDeptTreeList(deptList);
            // 存入redis缓存中
            redisService.setStringKeyAndValue("deptTree", deptTreeList);
            return deptTreeList;
        } else {
            // 有
            // 从redis中取出数据
            List<Department> deptTreeLists = (List<Department>) JSONObject.parse(data);
            return deptTreeLists;
        }
    }

    /**
     * 获取父级部门
     * @return 返回父级部门
     */
    @Override
    public List<Department> getParentDeptList() {
        return deptMapper.getParentDeptListBySql();
    }
}
