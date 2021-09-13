package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-13 21:40
 */
@RestController
@RequestMapping("/admin/product")
public class BaseAttrInfoController {

    @Autowired
    BaseAttrInfoService baseAttrInfoService;
    //http://api.gmall.com/admin/product/attrInfoList/{category1Id}/{category2Id}/{category3Id}
    /**
     * 获取分类id获取平台属性
     */
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(@PathVariable("category1Id") Long category1Id,
                                             @PathVariable("category2Id") Long category2Id,
                                             @PathVariable("category3Id") Long category3Id) {

        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoService.attrInfoList(category1Id,category2Id,category3Id);
        return Result.ok(baseAttrInfos);

    }
}
