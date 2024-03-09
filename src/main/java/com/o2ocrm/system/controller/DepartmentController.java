package com.o2ocrm.system.controller;

import com.o2ocrm.basic.query.BaseQuery;
import com.o2ocrm.basic.vo.AjaxResult;
import com.o2ocrm.system.domain.Department;
import com.o2ocrm.system.service.IDepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName:DepartmentController
 * @Description: Department控制层
 * @Author:zfl19
 * @CreateDate:2024/3/8 12:41
 */

@RestController
@RequestMapping("/system/dept")
public class DepartmentController {

    @Resource
    private IDepartmentService deptService;

    /**
     * 获取部门信息
     * @return 部门信息数据
     */
    @GetMapping("/all")
    public AjaxResult getAllDeptInfo() {
        return AjaxResult.success(deptService.getAllDeptInfo());
    }

    /**
     * 获取部门列表
     * @param query 分页参数
     * @return 部门列表
     */
    @PostMapping("/list")
    public AjaxResult getAllDeptInfoByPageList(@RequestBody BaseQuery query) {
        return AjaxResult.success(deptService.getAllDeptInfoByPageList(query));
    }

    /**
     * 删除部门
     * @param id 部门主键id
     * @return 返回信息
     */
    @DeleteMapping("/{id}")
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
    public AjaxResult insertAndModify(@RequestBody Department dept) {
        try {
            deptService.insertAndModify(dept);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("操作失败！");
        }
    }

}
