package com.o2ocrm.system.controller;

import com.o2ocrm.basic.vo.AjaxResult;
import com.o2ocrm.system.domain.Shop;
import com.o2ocrm.system.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 店铺注册
     * @param shop
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "店铺入驻", notes = "店铺注册")
    public AjaxResult shopRegister(@RequestBody Shop shop) {
        try {
            shopService.shopRegister(shop);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error("店铺注册失败，请稍后！");
        }

    }

    /**
     * 获取员工信息
     * @return 员工信息数据
     */
    @GetMapping("/all")
    @ApiOperation(value = "获取店铺信息", notes = "获取店铺信息")
    public AjaxResult getAllShopInfo() {
        return AjaxResult.success(shopService.getAllShopInfo());
    }

}
