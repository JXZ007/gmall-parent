package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author JXZ
 * @create 2021-09-16 17:39
 */
public interface ProductApiService {
    SkuInfo getSkuInfo(Long skuId);

    BaseCategoryView getCategoryView(Long category3Id);

    BigDecimal getSkuPrice(Long skuId);

    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId);

    Map getSkuValueIdsMap(Long spuId);
}
