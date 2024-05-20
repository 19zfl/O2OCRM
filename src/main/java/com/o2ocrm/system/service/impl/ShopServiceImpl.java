package com.o2ocrm.system.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.o2ocrm.system.domain.Shop;
import com.o2ocrm.system.mapper.EmployeeMapper;
import com.o2ocrm.system.mapper.ShopMapper;
import com.o2ocrm.system.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private EmployeeMapper empMapper;


    /**
     * 店铺入驻
     * @param shop
     */
    @Override
    public void shopRegister(Shop shop) {
        // 1.数据校验
        // 1.1 两次输入的密码是否一致
        if (!StrUtil.equals(shop.getAdmin().getPassword(), shop.getAdmin().getConfirmPassword()))
            throw new RuntimeException("两次输入的密码不一致！");
        // 1.2 校验店铺是否已经注册过
        List<Shop> shopList = shopMapper.getShopListByTel(shop.getTel());
        if (shopList.size() > 0 || shopList != null)
            throw new RuntimeException("店铺已经注册！");
        // 2. 密码加盐加密
        String saltV = IdUtil.fastSimpleUUID();     // 盐值
        String encryptedPassword = SecureUtil.md5().setSalt(saltV.getBytes()).digestHex(shop.getAdmin().getPassword());
        // 3. 将盐值和加密后的密码存入实体对象中
        shop.getAdmin().setSalt(saltV);
        shop.getAdmin().setPassword(encryptedPassword);
        // 4. 数据新增
        shopMapper.insertSelective(shop);
        shop.getAdmin().setShopId(shop.getId());
        empMapper.insertSelective(shop.getAdmin());
        shop.setAdminId(String.valueOf(shop.getAdmin().getId()));
        shopMapper.updateByPrimaryKeySelective(shop);
    }

}
