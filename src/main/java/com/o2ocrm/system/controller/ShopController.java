package com.o2ocrm.system.controller;

import com.o2ocrm.basic.vo.AjaxResult;
import com.o2ocrm.system.domain.Shop;
import com.o2ocrm.system.service.IShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:ShopController
 * @Description: Shop控制层
 * @Author:zfl19
 * @CreateDate:2024/5/19 20:22
 */

@RestController
@RequestMapping("/system/shop")
@Api(value = "ShopController", tags = {"店铺模块接口"})
public class ShopController {

    /** 注入业务层接口 */
    @Autowired
    private IShopService shopService;

    @PostMapping("/register")
    public AjaxResult shopRegister(@RequestBody Shop shop) {
        try {
            shopService.shopRegister(shop);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error("店铺失败，请稍后！");
        }

    }

}
