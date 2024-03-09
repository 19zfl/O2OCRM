package com.o2ocrm.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

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
public class Department {

    /** 部门id */
    @Id
    private Long id;

    /** 部门编号 */
    private String sn;

    /** 部门名称 */
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

}
