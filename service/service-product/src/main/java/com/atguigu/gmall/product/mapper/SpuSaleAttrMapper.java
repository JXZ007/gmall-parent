package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.bean.SkuAllSaleValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-15 22:23
 */
public interface SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {
    List<SpuSaleAttr> getSpuAttrAndValue(@Param("spuId") Long spuId);

    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@Param("skuId") Long skuId);

    List<SkuAllSaleValue> getSkuAllSaleValue(@Param("spuId") Long spuId);
}
