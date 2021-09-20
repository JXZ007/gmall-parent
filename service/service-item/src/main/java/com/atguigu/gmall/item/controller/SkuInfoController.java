package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author JXZ
 * @create 2021-09-16 15:38
 */
@RestController
@RequestMapping("/api/item")
public class SkuInfoController {



    @Autowired
    ItemService itemService;

    /**
     * 获取sku详情信息
     */
    @GetMapping("/{skuId}")
    public Result getItem(@PathVariable("skuId") Long skuId) {
        Map<String, Object> skuInfo = itemService.getSkuInfo(skuId);
        return Result.ok(skuInfo);
    }
}
