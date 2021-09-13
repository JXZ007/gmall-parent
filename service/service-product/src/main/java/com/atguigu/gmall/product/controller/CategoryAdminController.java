package com.atguigu.gmall.product.controller;



import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JXZ
 * @createDate 2021-09-13 15:35
 */
@RestController
@RequestMapping("/admin/product")
public class CategoryAdminController {
    @Autowired
    CategoryService categoryService;

    //http://api.gmall.com/admin/product/getCategory1

    /**
     * 获取一级分类信息
     *
     * @return
     */
    @GetMapping("/getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> category1s = categoryService.getCategory1();
        return Result.ok(category1s);
    }

    //http://api.gmall.com/admin/product/getCategory2/{category1Id}

    /**
     * 获取二级分类信息
     * @param category1Id
     * @return
     */
    @GetMapping("/getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable("category1Id") Long category1Id) {
        List<BaseCategory2> category2s = categoryService.getCategory2(category1Id);
        return Result.ok(category2s);

    }

    //http://api.gmall.com/admin/product/getCategory3/{category2Id}

    /**
     * 获取三级分类信息
     * @param category2Id
     * @return
     */
    @GetMapping("/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable("category2Id") Long category2Id) {
        List<BaseCategory3> category3s = categoryService.getCategory3(category2Id);
        return Result.ok(category3s);
    }
    //http://api.gmall.com/admin/product/attrInfoList/{category1Id}/{category2Id}/{category3Id}
    /**
     * 获取分类id获取平台属性
     */
/*    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<>*/
}
