package com.atguigu.gmall.feign.product;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author JXZ
 * @create 2021-09-16 15:32
 */
@FeignClient("service-product")
public interface SpuFeignClient {
    @GetMapping("/admin/product/{page}/{limit}")
    Result<Page<SpuInfo>> getPage(@PathVariable("page") Long page,
                                  @PathVariable("limit") Long limit,
                                  @RequestParam("category3Id") Long category3Id);
}
