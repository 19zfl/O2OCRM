package com.o2ocrm.basic.query;

import lombok.Data;

/**
 * @ClassName:BaseQuery
 * @Description: 接受前端传递的分页参数
 * @Author:zfl19
 * @CreateDate:2024/3/7 22:19
 * @Data: 需要使用getter和setter方法
 */

@Data
public class BaseQuery {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
