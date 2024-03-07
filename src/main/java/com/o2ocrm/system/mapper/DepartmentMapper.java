package com.o2ocrm.system.mapper;

import com.o2ocrm.system.domain.Department;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName:DepartmentMapper
 * @Description: Department持久层
 * @Author:zfl19
 * @CreateDate:2024/3/7 19:52
 */

public interface DepartmentMapper extends Mapper<Department> {

    /**
     * 基本的CRUD通过继承tk提供的Mapper接口获得
     */

}
