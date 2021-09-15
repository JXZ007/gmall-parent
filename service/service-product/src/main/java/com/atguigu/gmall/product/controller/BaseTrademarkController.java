package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌接口
 *
 * @author JXZ
 * @create 2021-09-14 14:54
 */
@RestController
@RequestMapping("/admin/product")
@Slf4j
public class BaseTrademarkController {

    @Autowired
    BaseTrademarkService baseTrademarkService;

    //http://api.gmall.com/admin/product/baseTrademark/{page}/{limit}

    /**
     * 获取品牌分页列表
     */
    @GetMapping("/baseTrademark/{page}/{limit}")
    public Result<Page<BaseTrademark>> page(@PathVariable("page") Long page,
                                            @PathVariable("limit") Long limit) {

        Page<BaseTrademark> pageModel = new Page<>(page, limit);
        baseTrademarkService.page(pageModel);
        return Result.ok(pageModel);
    }

    //http://api.gmall.com/admin/product/baseTrademark/save

    /**
     * 添加品牌
     */
    @PostMapping("/baseTrademark/save")
    public Result save(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/baseTrademark/update

    /**
     * 修改品牌
     */
    @PutMapping("/baseTrademark/update")
    public Result update(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/baseTrademark/remove/{id}

    /**
     * 删除品牌
     */
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result delete(@PathVariable("id") Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/baseTrademark/get/{id}

    /**
     * 根据id获取品牌
     */
    @GetMapping("/baseTrademark/get/{id}")
    public Result<BaseTrademark> getById(@PathVariable("id") Long id) {
        BaseTrademark trademark = baseTrademarkService.getById(id);
        return Result.ok(trademark);

    }

    //http://api.gmall.com/admin/product/baseTrademark/getTrademarkList

    /**
     * 获取品牌属性
     */
    @GetMapping("/baseTrademark/getTrademarkList")
    public Result<List<BaseTrademark>> getTrademarkList() {
        List<BaseTrademark> list = baseTrademarkService.list();
        return Result.ok(list);
    }
}
