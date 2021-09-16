package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JXZ
 * @create 2021-09-16 9:28
 */
@RestController
@RequestMapping("/admin/product")
public class SkuInfoController {

    @Autowired
    SkuInfoService skuInfoService;

    //http://api.gmall.com/admin/product/saveSkuInfo
    /**
     * 添加sku
     */
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuInfoService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/list/{page}/{limit}

    /**
     * 获取sku分页列表
     */
    @GetMapping("/list/{page}/{limit}")
    public Result<Page<SkuInfo>> getPage(@PathVariable("page") Long page,
                                   @PathVariable("limit") Long limit) {
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);
        skuInfoService.page(skuInfoPage);
        return Result.ok(skuInfoPage);
    }
}
