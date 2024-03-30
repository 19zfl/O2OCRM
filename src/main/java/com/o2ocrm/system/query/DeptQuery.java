package com.o2ocrm.system.query;

import com.o2ocrm.basic.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName:DeptQuery
 * @Description: 部门模块高级查询参数
 * @Author:zfl19
 * @CreateDate:2024/3/30 13:22
 */

@Data
@ApiModel(value = "DeptQuery", description = "部门模块高级查询参数")
public class DeptQuery extends BaseQuery {

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    private String name;

    /** 部门经理id */
    @ApiModelProperty(value = "部门经理id")
    private Long managerId;

    /** 部门上级部门id */
    @ApiModelProperty(value = "部门上级部门id")
    private Long parentId;

}
