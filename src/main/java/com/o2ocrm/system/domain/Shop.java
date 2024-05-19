package com.o2ocrm.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.o2ocrm.basic.entity.BaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @ClassName:Shop
 * @Description: 店铺实体类
 * @Author:zfl19
 * @CreateDate:2024/5/19 19:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_shop")
@ApiModel(value = "Shop", description = "店铺实体类")
public class Shop extends BaseDomain {

    /** 店铺名称 */
    private String name;

    /** 店铺联系电话 */
    private String tel;

    /** 店铺注册时间 */
    @Column(name = "register_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String registerTime;

    /** 店铺当前状态 */
    private String state;

    /** 店铺地址 */
    private String address;

    /** 店铺logo */
    private String logo;

    /** 店铺管理员 */
    @Column(name = "admin_id")
    private String adminId;

}
