package com.o2ocrm.basic.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName:BaseDomain
 * @Description: 实体类公共属性
 * @Author:zfl19
 * @CreateDate:2024/3/30 18:51
 */

@Data
public class BaseDomain {

    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
