package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author JXZ
 * @create 2021-09-15 20:26
 */
public interface SpuService extends IService<SpuInfo> {
    void bigSaveSpu(SpuInfo spuInfo);
}
