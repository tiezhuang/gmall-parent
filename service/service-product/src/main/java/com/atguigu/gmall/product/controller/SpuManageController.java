package com.atguigu.gmall.product.controller;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 罗铁壮
 * @create 2020-12-01 16:39
 */
@RestController
@RequestMapping("admin/product")
public class SpuManageController {
    @Autowired
    private ManageService manageService;

    /**
     * 分页获取spu信息
     * @param page
     * @param size
     * @param spuInfo
     * @return
     */
    @GetMapping("{page}/{size}")
    public Result getSpuInfoPage(@PathVariable long page,
                                 @PathVariable long size,
                                 SpuInfo spuInfo){
        Page<SpuInfo> spuInfoPage = new Page<>(page, size);
        IPage<SpuInfo> spuInfoPage1 = manageService.getSpuInfoPage(spuInfoPage, spuInfo);
        return Result.ok(spuInfoPage1);
    }
    /**
     * 获取全部销售属性
     * @return
     */
    @GetMapping("baseSaleAttrList")
    public Result baseSaleAttrList(){
        return Result.ok(manageService.baseSaleAttrList());
    }

    /**
     * 保存spu信息
     * @param spuInfo
     * @return
     */
    @PostMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

}
