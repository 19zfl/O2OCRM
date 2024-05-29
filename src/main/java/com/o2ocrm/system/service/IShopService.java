package com.o2ocrm.system.service;

import com.o2ocrm.system.domain.Employee;
import com.o2ocrm.system.domain.Shop;

import java.util.List;

/**
 * @ClassName:IShopService
 * @Description: Shop业务层接口
 * @Author:zfl19
 * @CreateDate:2024/5/19 20:18
 */

public interface IShopService {

    /** 店铺入驻 */
    void shopRegister(Shop shop);

    /**
     * 获取所有店铺信息
     * @return 所有店铺信息集合List
     */
    List<Shop> getAllShopInfo();

}
