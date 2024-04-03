package com.o2ocrm.system.query;

import com.o2ocrm.basic.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:EmpQuery
 * @Description: 员工模块高级查询参数
 * @Author:zfl19
 * @CreateDate:2024/3/30 13:25
 */

@Data
@ApiModel(value = "EmpQuery", description = "员工模块高级查询参数")
public class EmpQuery extends BaseQuery {

    /** 员工名称 */
    @ApiModelProperty(value = "部门名称")
    private String name;

}
