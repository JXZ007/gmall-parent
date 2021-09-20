package com.atguigu.gmall.web.all.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.item.SkuItemFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author JXZ
 * @create 2021-09-16 14:41
 */
@Controller
public class SkuItemController {

    @Autowired
    SkuItemFeignClient skuItemFeignClient;

    @GetMapping("/{skuId}.html")
    public String getSkuInfo(@PathVariable("skuId") Long skuId,
                             Model model) {
        Result<Map<String, Object>> item = skuItemFeignClient.getItem(skuId);
        Map<String, Object> data = item.getData();

        model.addAllAttributes(data);
        return "item/index";
    }
}
