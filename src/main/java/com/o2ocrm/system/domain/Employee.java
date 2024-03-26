package com.o2ocrm.system.domain;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName:Employee
 * @Description: 员工实体类
 * @Author:zfl19
 * @CreateDate:2024/3/26 23:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_employee")
@ApiModel(value = "Employee", description = "员工实体类")
public class Employee {

    /** 员工id */
    @Id
    private Long id;

    /** 员工名字 */
    private String username;

    /** 员工邮箱 */
    private String email;

    /** 员工电话 */
    private String phone;

    /** 员工密码 */
    private String password;

    /** 员工年龄 */
    private Integer age;

    /** 员工信息状态 */
    private Integer state;

    /** 员工所属部门id */
    private Long department_id;

    /** 员工登录信息id */
    private Long logininfo_id;

    /** 员工店铺id */
    private Long shop_id;

    /** 员工上级部门id */
    private Long parent_id;

    /** 员工经理id */
    private Long manager_id;

}
