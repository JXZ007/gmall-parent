package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.bean.SkuAllSaleValue;
import com.atguigu.gmall.product.mapper.BaseCategoryViewMapper;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.service.ProductApiService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JXZ
 * @create 2021-09-16 17:39
 */
@Service
public class ProductApiServiceImpl implements ProductApiService {

    @Autowired
    SkuInfoMapper skuInfoMapper;
    @Autowired
    SkuImageMapper skuImageMapper;
    @Autowired
    BaseCategoryViewMapper baseCategoryViewMapper;
    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;
    //1、获取sku基本信息和图片列表
    @Override
    public SkuInfo getSkuInfo(Long skuId) {
        // 1、获取基本信息
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        //2、查询图片列表，数据库中没有图片列表
        List<SkuImage> imageList = skuImageMapper.selectList(new QueryWrapper<SkuImage>().eq("sku_id", skuId));
        // 3、属性赋值
        skuInfo.setSkuImageList(imageList);
        return skuInfo;
    }
    // 2、获取三级分类
    @Override
    public BaseCategoryView getCategoryView(Long category3Id) {
        return baseCategoryViewMapper.selectById(category3Id);
    }

    // 3、获取sku当前价格
    @Override
    public BigDecimal getSkuPrice(Long skuId) {
        return skuInfoMapper.getSkuPrice(skuId);
    }
    // 4、查询当前sku所在的spu
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId) {
        return spuSaleAttrMapper.getSpuSaleAttrListCheckBySku(skuId);

    }

    @Override
    public Map getSkuValueIdsMap(Long spuId) {
        List<SkuAllSaleValue> skuAllSaleValue = spuSaleAttrMapper.getSkuAllSaleValue(spuId);
        Map<String, String> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(skuAllSaleValue)) {
            for (SkuAllSaleValue saleValue : skuAllSaleValue) {
                map.put(saleValue.getValueIds(), saleValue.getSkuId());
            }
        }
        return map;
    }

}
