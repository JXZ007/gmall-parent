package com.atguigu.gmall.product.api;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 商品详情页所需要的数据
 *
 * @author JXZ
 * @create 2021-09-16 17:01
 */


@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    ProductApiService productApiService;

    // 1、sku基本信息（名字，id，价格，sku描述） skuInfo
    // 2、SKu图片信息（sku默认图片，一级sku_image 一组图片）
    @GetMapping("/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId) {
        return productApiService.getSkuInfo(skuId);

    }

    // 3、Sku分类信息（sku_info【只有三级分类】 根据这个三级分类id查出所在的一级，二级分类）
    @GetMapping("/inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable("category3Id") Long category3Id) {
        return productApiService.getCategoryView(category3Id);
    }
    // 4、sku销售属性相关的信息（查出自己的sku组合，还要查出这个sku所在的spu定义了所有的sku组合）

    /**
     * 功能需求
     * ①、查询出当前sku所在的spu下的所有销售属性和销售属性值
     * ②、并且高亮显示当前sku商品
     * ③、选择不同的组合，跳转到指定的sku页面
     */
    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable("skuId") Long skuId) {

        return productApiService.getSpuSaleAttrListCheckBySku(skuId);
    }
    /**
     * 5、查询出spu下面的所有sku销售属性值可切换的信息
     *   4.3、点击其他销售属性值的组合，跳转到另外的sku页面
     */
    @GetMapping("inner/getSkuValueIdsMap/{spuId}")
    public Map getSkuValueIdsMap(@PathVariable("spuId") Long spuId){
        return productApiService.getSkuValueIdsMap(spuId);
    }


    // 5、sku价格信息（平台可以单独修改价格，sku后续会放入缓存，为了回显最新价格，所以要查）
    @GetMapping("/inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable("skuId") Long skuId) {
        return productApiService.getSkuPrice(skuId);
    }
}
