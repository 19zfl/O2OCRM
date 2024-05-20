package com.o2ocrm.system.service;

import com.o2ocrm.system.domain.Shop;

/**
 * @ClassName:IShopService
 * @Description: Shop业务层接口
 * @Author:zfl19
 * @CreateDate:2024/5/19 20:18
 */

public interface IShopService {

    /** 店铺入驻 */
    void shopRegister(Shop shop);

}
