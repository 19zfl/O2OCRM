package com.o2ocrm.system.controller;

import com.o2ocrm.basic.vo.AjaxResult;
import com.o2ocrm.system.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
