package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-13 21:42
 */
public interface BaseAttrInfoService {
    List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id);
}
