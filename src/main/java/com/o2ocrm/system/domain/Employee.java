package com.o2ocrm.system.domain;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.o2ocrm.basic.entity.BaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

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
public class Employee extends BaseDomain {

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
    @Column(name = "department_id")
    private Long departmentId;

    /** 员工登录信息id */
    @Column(name = "logininfo_id")
    private Long logininfoId;

    /** 员工店铺id */
    @Column(name = "shop_id")
    private Long shopId;

    /** 员工上级部门id */
    @Column(name = "parent_id")
    private Long parentId;

    /** 员工经理id */
    @Column(name = "manager_id")
    private Long managerId;

    /** 密码盐值 */
    private String salt;

    /** 确认密码 */
    @Transient
    private String confirmPassword;

//    public void passWordInitializer() {
//        String saltV = IdUtil.fastSimpleUUID();
//        String encryptedPassword = SecureUtil.md5().setSalt(saltV.getBytes()).digestHex(this.getPassword());
//        this.setSalt(saltV);
//        this.setPassword(encryptedPassword);
//    }

}
