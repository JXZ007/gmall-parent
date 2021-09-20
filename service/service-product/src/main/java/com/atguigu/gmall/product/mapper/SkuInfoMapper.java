package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author JXZ
 * @create 2021-09-16 9:29
 */
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
    void onSale(@Param("skuId") Long skuId);

    void cancelSale(@Param("skuId") Long skuId);

    BigDecimal getSkuPrice(@Param("skuId") Long skuId);
}
