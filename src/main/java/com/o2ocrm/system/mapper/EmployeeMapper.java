package com.o2ocrm.system.mapper;

import com.o2ocrm.system.domain.Employee;
import tk.mybatis.mapper.common.Mapper;

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

}
