package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.feign.product.SkuInfoFeignClient;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.util.StringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedissonClient redissonClient;

    /**
     * 远程查询sku信息
     *
     * @param skuId
     * @return
     */
    @Override
    public Map<String, Object> getSkuInfo(Long skuId) {
//        return getSkuInfoWithRedIsLock01(skuId);

        // redisson 分布式锁
        log.info("获取分布式锁");
        RLock lock = redissonClient.getLock("lock:"+skuId);// 获取一把锁
        Map<String, Object> cache = getSkuInfoFromCache(skuId);
        if (cache == null) {
            log.info("缓存为空");
            try {
                //缓存中没有
                log.info("尝试加锁");
                lock.lock();
                log.info("加锁成功....尝试继续命中缓存....");
                Map<String, Object> cacheAgain = getSkuInfoFromCache(skuId);
                if (cacheAgain == null) {
                    log.info("开始远程查询数据库");
                    Map<String, Object> data = getFromServiceItemFeign(skuId);
                    saveToCache(data, skuId);
                    return data;
                }
                log.info("缓存命中");
                return cacheAgain;
            } finally {
                lock.unlock();

            }
        }
        log.info("缓存命中");
        return cache;

    }

    // 第一版从缓存中获取数据
    private Map<String, Object> getSkuInfoWithRedIsLock01(Long skuId) {
        Map<String, Object> skuInfoFromCache = getSkuInfoFromCache(skuId);
        if (skuInfoFromCache != null) {
            return skuInfoFromCache;

        } else {
            //缓存中没有
            // 查询数据库
            Map<String, Object> fromServiceItemFeign = getFromServiceItemFeign(skuId);
            // 存入缓存
            saveToCache(fromServiceItemFeign, skuId);
            return fromServiceItemFeign;
        }
    }

    /**
     * 抽取从缓存中获取数据
     */
    @SneakyThrows// lombok 自动抛出异常
    public Map<String, Object> getSkuInfoFromCache(Long skuId) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String key = operations.get("sku:info:" + skuId);
        ObjectMapper objectMapper = new ObjectMapper();
        if (StringUtil.isEmpty(key)) {
            return null;
        } else {
            return objectMapper.readValue(key, new TypeReference<Map<String, Object>>() {
            });

        }

    }

    /**
     * 保存数据到redis缓存
     */
    @SneakyThrows
    void saveToCache(Map<String, Object> data, Long skuId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(data);
        stringRedisTemplate.opsForValue().set("sku:info:" + skuId, jsonStr);
    }

    /**
     * 从远程数据库客户端查询数据
     *
     * @param skuId
     * @return
     */
    private Map<String, Object> getFromServiceItemFeign(Long skuId) {
        log.info("开始远程查询，远程会操作数据库");
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
