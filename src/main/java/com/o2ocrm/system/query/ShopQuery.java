package com.o2ocrm.system.query;

import com.o2ocrm.basic.query.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName:ShopQuery
 * @Description: 店铺模块高级查询参数
 * @Author:zfl19
 * @CreateDate:2024/5/29 16:03
 */

@Data
@ApiModel(value = "ShopQuery", description = "店铺模块高级查询参数")
public class ShopQuery extends BaseQuery {
}
