package com.o2ocrm.basic.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName:PageList
 * @Description: 分页数据返回工具类
 * @Author:zfl19
 * @CreateDate:2024/3/7 21:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageList<T> {

    /**
     * 分页数据总条数
     */
    private Long total;

    /**
     * 分页数据集合
     */
    private List<T> list;

}
