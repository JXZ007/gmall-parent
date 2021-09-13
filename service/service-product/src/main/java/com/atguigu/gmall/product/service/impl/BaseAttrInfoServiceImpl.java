package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-13 21:43
 */
@Service
public class BaseAttrInfoServiceImpl implements BaseAttrInfoService {
    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    /**
     * 获取分类id获取平台属性
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id) {

       return baseAttrInfoMapper.getAttrInfoList();
    }
}
