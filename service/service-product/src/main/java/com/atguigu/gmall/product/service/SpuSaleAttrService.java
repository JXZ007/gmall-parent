package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-15 23:55
 */
public interface SpuSaleAttrService  extends IService<SpuSaleAttr> {
    List<SpuSaleAttr> getSpuAttrAndValue(Long spuId);
}
