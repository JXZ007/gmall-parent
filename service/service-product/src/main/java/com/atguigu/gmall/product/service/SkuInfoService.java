package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author JXZ
 * @create 2021-09-16 9:29
 */
public interface SkuInfoService extends IService<SkuInfo> {
    void saveSkuInfo(SkuInfo skuInfo);

    void onSale(Long skuId);

    void cancelSale(Long skuId);
}
