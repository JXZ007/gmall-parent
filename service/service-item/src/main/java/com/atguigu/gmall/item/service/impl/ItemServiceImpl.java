package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.feign.product.SkuInfoFeignClient;
import com.atguigu.gmall.feign.product.SpuFeignClient;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JXZ
 * @create 2021-09-16 16:22
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    SkuInfoFeignClient skuInfoFeignClient;

    /**
     * 远程查询sku信息
     *
     * @param skuId
     * @return
     */
    @Override
    public Map<String, Object> getSkuInfo(Long skuId) {
        Map<String, Object> result = new HashMap<>();
        // 1、sku基本信息（名字，id，价格，sku描述） skuInfo
        // 2、SKu图片信息（sku默认图片，一级sku_image 一组图片）
        SkuInfo skuInfo = skuInfoFeignClient.getSkuInfo(skuId);
        if (skuInfo != null) {
            result.put("skuInfo", skuInfo);
            // 3、Sku分类信息（sku_info【只有三级分类】 根据这个三级分类id查出所在的一级，二级分类）
            BaseCategoryView categoryView = skuInfoFeignClient.getCategoryView(skuInfo.getCategory3Id());
            result.put("categoryView", categoryView);
            // 4、sku销售属性相关的信息（查出自己的sku组合，还要查出这个sku所在的spu定义了所有的sku组合）
            List<SpuSaleAttr> spuSaleAttrList = skuInfoFeignClient.getSpuSaleAttrListCheckBySku(skuId);
            result.put("spuSaleAttrList", spuSaleAttrList);
            // 5、sku价格信息（平台可以单独修改价格，sku后续会放入缓存，为了回显最新价格，所以要查）
            BigDecimal price = skuInfoFeignClient.getSkuPrice(skuId);
            result.put("price", price);
            // 6、sku所在的spu下的所有sku组合信息
            Map map = skuInfoFeignClient.getSkuValueIdsMap(skuInfo.getSpuId());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String jsonStr = objectMapper.writeValueAsString(map);
                result.put("valuesSkuJson", jsonStr);
                log.info("打印内容：" + jsonStr);
            } catch (JsonProcessingException e) {
                log.error("商品sku组合数据转换异常：{}", e);
            }

        }


        return result;
    }


}
