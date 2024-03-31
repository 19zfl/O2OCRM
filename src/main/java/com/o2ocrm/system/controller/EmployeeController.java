package com.o2ocrm.system.controller;

import com.o2ocrm.basic.vo.AjaxResult;
import com.o2ocrm.system.domain.Employee;
import com.o2ocrm.system.query.EmpQuery;
import com.o2ocrm.system.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:EmployeeController
 * @Description: Employee控制层
 * @Author:zfl19
 * @CreateDate:2024/3/26 23:25
 */

@RestController
@RequestMapping("/system/emp")
@Api(value = "EmployeeController", tags = {"员工模块接口"})
public class EmployeeController {

    /** 注入业务层接口实现类 */
    @Autowired
    private IEmployeeService empService;

    /**
     * 获取员工信息
     * @return 员工信息数据
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取员工信息", notes = "获取员工信息")
    public AjaxResult getAllEmpInfo() {
        return AjaxResult.success(empService.getAllEmpInfo());
    }

    /**
     * 获取员工列表
     * @param query 分页参数
     * @return 员工列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取员工列表", notes = "获取员工列表")
    public AjaxResult getAllEmpInfoByPageList(@ApiParam(value = "分页参数", required = true) @RequestBody EmpQuery query) {
        return AjaxResult.success(empService.getAllEmpInfoByPageList(query));
    }

    /**
     * 删除员工
     * @param id 员工主键id
     * @return 返回信息
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除员工", notes = "删除员工")
    public AjaxResult deleteEmpInfoById(@PathVariable Long id) {
        try {
            empService.deleteEmpInfoById(id);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("删除失败！");
        }
    }

    /**
     * 批量删除员工
     * @param query 员工id集合
     * @return 返回信息
     */
    @PostMapping("/del/batch/")
    @ApiOperation(value = "批量删除员工", notes = "批量删除员工")
    public AjaxResult batchDeleteEmpInfoByIds(@RequestBody EmpQuery query) {
        try {
            empService.batchDeleteDeptInfoByIds(query);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error("批量操作失败！");
        }
    }

    /**
     * 员工新增和修改
     * @param emp 员工信息
     * @return 返回消息
     */
    @PostMapping("/edit")
    @ApiOperation(value = "员工新增和修改", notes = "员工新增和修改")
    public AjaxResult insertAndModify(@RequestBody Employee emp) {
        try {
            empService.insertAndModify(emp);
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("操作失败！");
        }
    }

    /**
     * 获取管理部门的经理列表
     * @return
     */
    @GetMapping("/has/dept")
    @ApiOperation(value = "获取管理部门的经理列表", notes = "获取管理部门的经理列表")
    public AjaxResult getHasDeptManagerList() {
        try {
            List<Employee> hasDeptManagerList = empService.getHasDeptManagerList();
            return AjaxResult.success(hasDeptManagerList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("经理列表获取失败！");
        }
    }

}