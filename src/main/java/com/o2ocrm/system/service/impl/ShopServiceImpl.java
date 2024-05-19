package com.o2ocrm.system.service.impl;

import com.o2ocrm.system.mapper.ShopMapper;
import com.o2ocrm.system.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:ShopServiceImpl
 * @Description: Shop业务层接口实现类
 * @Author:zfl19
 * @CreateDate:2024/5/19 20:19
 */

@Service
public class ShopServiceImpl implements IShopService {

    /** 注入持久层依赖 */
    @Autowired
    private ShopMapper shopMapper;



}
