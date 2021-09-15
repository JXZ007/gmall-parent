package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-15 20:16
 */
@RestController
@RequestMapping("/admin/product")
public class SpuController {
    @Autowired
    SpuService spuService;
    @Autowired
    SpuImageService spuImageService;
    @Autowired
    SpuSaleAttrService spuSaleAttrService;


    //http://api.gmall.com/admin/product/{page}/{limit}?category3Id=61

    /**
     * 获取spu分页列表
     */
    @GetMapping("/{page}/{limit}")
    public Result<Page<SpuInfo>> getPage(@PathVariable("page") Long page,
                                         @PathVariable("limit") Long limit,
                                         @RequestParam("category3Id") Long category3Id) {
        Page<SpuInfo> pageModel = new Page<>(page, limit);
        spuService.page(pageModel, new QueryWrapper<SpuInfo>().eq("category3_id", category3Id));
        return Result.ok(pageModel);
    }

    //http://api.gmall.com/admin/product/saveSpuInfo

    /**
     * 保存spu
     */
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        spuService.bigSaveSpu(spuInfo);
        return Result.ok();
    }


    //http://api.gmall.com/admin/product/spuImageList/{spuId}

    /**
     * 根据spuid获取图片spu列表
     */
    @GetMapping("/spuImageList/{spuId}")
    public Result<List<SpuImage>> spuImageList(@PathVariable("spuId") Long spuId) {

        List<SpuImage> spuImages = spuImageService.list(new QueryWrapper<SpuImage>().eq("spu_id", spuId));
        return Result.ok(spuImages);
    }

    //http://api.gmall.com/admin/product/spuSaleAttrList/{spuId}
    /**
     * 根据spuId获取销售属性
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result<List<SpuSaleAttr>> spuSaleAttrList(@PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> list=   spuSaleAttrService.getSpuAttrAndValue(spuId);
        return Result.ok(list);
    }
}

