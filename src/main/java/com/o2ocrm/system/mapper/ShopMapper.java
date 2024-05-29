package com.o2ocrm.system.mapper;

import com.o2ocrm.system.domain.Shop;
import com.o2ocrm.system.query.ShopQuery;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName:ShopMapper
 * @Description: Shop持久层
 * @Author:zfl19
 * @CreateDate:2024/5/19 20:13
 */

public interface ShopMapper extends Mapper<Shop> {

    /**
     * 基本的CRUD通过继承tk提供的Mapper接口获得
     */

    /**
     * 通过电话号码查询店铺信息
     * @param tel 电话号码
     * @return 店铺信息
     */
    List<Shop> getShopListByTel(String tel);

    /**
     * 连表查询获取部门数据
     * @return 所有部门分页信息集合
     */
    List<Shop> getAllShopListBySql(ShopQuery query);

}
