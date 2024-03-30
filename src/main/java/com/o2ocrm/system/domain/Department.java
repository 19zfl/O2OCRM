package com.o2ocrm.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.o2ocrm.basic.entity.BaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:Department
 * @Description: 部门实体类
 * @Author:zfl19
 * @CreateDate:2024/3/7 18:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_department")
@ApiModel(value = "Department", description = "部门实体类")
public class Department extends BaseDomain {

    /** 部门编号 */
//    @Size(min = 3, max = 8, message = "长度在3-8个字母或汉字！")
    private String sn;

    /** 部门名称 */
//    @NotBlank(message = "不能为空！")
    private String name;

    /**
     * 部门状态
     * 1：启用，0：禁用
     */
    private Integer state = 1;

    /** 部门经理id */
    private Long managerId;

    /** 上级部门id */
    private Long parentId;

    /** 部门路径 */
    private String dirPath;

    /** 部门经理名字 */
    @Transient
    private String managerName;

    /** 上级部门名称 */
    @Transient
    private String parentName;

    /** 子部门集合 */
    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Department> children = new ArrayList<>();

    /** 部门数据结构 */
    @Transient
    private Long[] parentIds;

}
