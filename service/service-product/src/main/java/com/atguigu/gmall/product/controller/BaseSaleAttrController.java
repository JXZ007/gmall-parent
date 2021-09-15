package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-15 20:46
 */
@RestController
@RequestMapping("/admin/product")
public class BaseSaleAttrController {

    @Autowired
    BaseSaleAttrService baseSaleAttrService;
    //http://api.gmall.com/admin/product/baseSaleAttrList
    /**
     * 获取销售属性
     */
    @GetMapping("/baseSaleAttrList")
    public Result<List<BaseSaleAttr>> baseSaleAttrList() {
        List<BaseSaleAttr> list = baseSaleAttrService.list();
        return Result.ok(list);

    }
}
