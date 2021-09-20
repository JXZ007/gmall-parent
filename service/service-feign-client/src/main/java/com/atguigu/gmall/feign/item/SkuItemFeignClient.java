package com.atguigu.gmall.feign.item;

import com.atguigu.gmall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/item")
@FeignClient("service-item")
public interface SkuItemFeignClient {

    @GetMapping("/{skuId}")
    Result getItem(@PathVariable Long skuId);
}
