package com.atguigu.gmall.item.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 罗铁壮
 * @create 2020-12-04 16:30
 */
@RestController
@RequestMapping("api/item")
public class ItemApiController {
    @Autowired
    private ItemService itemService;

    /**
     * 根据skuiD获取sku商品详情页信息
     * @param skuId
     * @return
     */
    @GetMapping("{skuId}")
    public Result getItem(long skuId){
        return Result.ok(itemService.getskuInfoByskuId(skuId));
    }
}
