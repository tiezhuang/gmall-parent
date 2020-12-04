package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-12-02 17:55
 */
@RestController
@RequestMapping("admin/product")
public class SkuManageController {
    @Autowired
    private ManageService manageService;

    /**
     * 根据spuId获取spu图片路径以list集合返回
     * @param spuId
     * @return
     */
    @GetMapping("spuImageList/{spuId}")
    public Result spuImageList(@PathVariable long spuId){
       List<SpuImage> spuImageList = manageService.spuImageList(spuId);
        return Result.ok(spuImageList);
    }

    /**
     * 根据spuId获取销售属性以list集合返回
     * @param spuId
     * @return
     */
    @GetMapping("spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable long spuId){
       List<SpuSaleAttr> spuSaleAttrLists =  manageService.spuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrLists);

    }

    /**
     * 保存sku信息
     * @param skuInfo
     * @return
     */
    @PostMapping("saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
            manageService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * 分页获取sku信息
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit){
        return Result.ok(manageService.getSkuInfoList(new Page<SkuInfo>(page,limit)));
    }

    /**
     * 上架sku
     * @param skuId
     * @return
     */
    @GetMapping("onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){
        manageService.onSales(skuId);
        return Result.ok();
    }
    /**
     * 上架sku
     * @param skuId
     * @return
     */
    @GetMapping("cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){
        manageService.cancelSales(skuId);
        return Result.ok();
    }
}
