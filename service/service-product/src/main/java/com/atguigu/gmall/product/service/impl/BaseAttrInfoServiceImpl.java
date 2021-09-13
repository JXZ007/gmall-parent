package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author JXZ
 * @create 2021-09-13 21:43
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo> implements BaseAttrInfoService {
    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    /**
     * 获取分类id获取平台属性
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id) {

        return baseAttrInfoMapper.getAttrInfoList();
    }

    /**
     * 添加或更新平台属性
     *
     * @param baseAttrInfo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateInfoAndValue(BaseAttrInfo baseAttrInfo) {
        if (baseAttrInfo.getId() == null) {
            saveInfoAndValue(baseAttrInfo);
        } else {
            updateInfoAndValue(baseAttrInfo);
        }

    }

    /**
     * 更新平台属性
     *
     * @param baseAttrInfo
     */
    private void updateInfoAndValue(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.updateById(baseAttrInfo);
        Long baseAttrInfoId = baseAttrInfo.getId();
        //没有单条属性值的删除，只有删除现有的所有属性值，再新保存属性值，达到更新的效果
        QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", baseAttrInfoId);
        baseAttrValueMapper.delete(queryWrapper);
        // 不再进行校验，因为更新可能就是删除所有的属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue baseAttrValue : attrValueList) {
            baseAttrValue.setAttrId(baseAttrInfoId);
            baseAttrValueMapper.insert(baseAttrValue);
        }


    }

    /**
     * 新增平台属性
     *
     * @param baseAttrInfo
     */
    private void saveInfoAndValue(BaseAttrInfo baseAttrInfo) {
        // 新增加平台属性
        baseAttrInfoMapper.insert(baseAttrInfo);
        // 获取自增主键
        Long baseAttrInfoId = baseAttrInfo.getId();
        //填充 attrValueList
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (!CollectionUtils.isEmpty(attrValueList)) {
            for (BaseAttrValue baseAttrValue : attrValueList) {
                baseAttrValue.setAttrId(baseAttrInfoId);
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }
    }
}
