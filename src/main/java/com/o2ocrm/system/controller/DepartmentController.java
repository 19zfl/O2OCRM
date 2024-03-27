package com.o2ocrm.system.controller;

import com.o2ocrm.basic.query.BaseQuery;
import com.o2ocrm.basic.vo.AjaxResult;
import com.o2ocrm.system.domain.Department;
import com.o2ocrm.system.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:DepartmentController
 * @Description: Department控制层
 * @Author:zfl19
 * @CreateDate:2024/3/8 12:41
 */

@RestController
@RequestMapping("/system/dept")
@Api(value = "DepartmentController", tags = {"部门模块接口"})
public class DepartmentController {

    /** 注入业务层接口实现类 */
    @Resource
    private IDepartmentService deptService;

    /**
     * 获取部门信息
     * @return 部门信息数据
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取部门信息", notes = "获取部门信息")
    public AjaxResult getAllDeptInfo() {
        return AjaxResult.success(deptService.getAllDeptInfo());
    }

    /**
     * 获取部门列表
     * @param query 分页参数
     * @return 部门列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取部门列表", notes = "获取部门列表")
    public AjaxResult getAllDeptInfoByPageList(@ApiParam(value = "分页参数", required = true) @RequestBody BaseQuery query) {
        return AjaxResult.success(deptService.getAllDeptInfoByPageList(query));
    }

    /**
     * 删除部门
     * @param id 部门主键id
     * @return 返回信息
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public AjaxResult deleteDeptInfoById(@PathVariable Long id) {
        try {
            deptService.deleteDeptInfoById(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败！");
        }
    }

    /**
     * 部门新增和修改
     * @param dept 部门信息
     * @return 返回消息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "部门新增和修改", notes = "部门新增和修改")
    public AjaxResult insertAndModify(@RequestBody Department dept) {
        try {
            deptService.insertAndModify(dept);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("操作失败！");
        }
    }

    /**
     * 获取部门含子集的数据
     * @return 返回部门数据含子集
     */
    @GetMapping("/tree")
    @ApiOperation(value = "部门无限极树列表展示", notes = "新增模态框中部门无限极树列表展示")
    public AjaxResult getTreeDeptList() {
        try {
            List<Department> treeDeptList = deptService.getTreeDeptList();
            return AjaxResult.success(treeDeptList);
        } catch (Exception e) {
            return AjaxResult.error("获取树失败！");
        }
    }

    /**
     * 获取父级部门
     * @return 返回父级部门
     */
    @GetMapping("/parent")
    @ApiOperation(value = "首页父级部门下拉列表", notes = "部门模块首页父级部门下拉列表展示")
    public AjaxResult getParentDeptList() {
        try {
            List<Department> parentDeptList = deptService.getParentDeptList();
            return AjaxResult.success(parentDeptList);
        } catch (Exception e) {
            return AjaxResult.error("父级部门获取失败！");
        }
    }

}
