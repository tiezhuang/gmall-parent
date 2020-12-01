package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 罗铁壮
 * @create 2020-12-01 17:25
 */
@RestController
@RequestMapping("/admin/product/baseTrademark")
public class BaseTrademarkController {
    @Autowired
    private BaseTrademarkService baseTrademarkService;

    /**
     * 分页获取品牌数据
     * @param page
     * @param size
     * @return
     */
    @GetMapping("{page}/{size}")
    public Result getBaseTrademarkList(@PathVariable Long page,
                                       @PathVariable Long size){
        IPage<BaseTrademark> baseTrademarkIPage = baseTrademarkService.selectPage(new Page<BaseTrademark>(page, size));
        return Result.ok(baseTrademarkIPage);
    }
    /**
     * 根据品牌Id
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public Result getBaseTrademark(@PathVariable Long id){
        return Result.ok(baseTrademarkService.getById(id));
    }

    /**
     * 根据传过来的品牌信息，保存品牌
     * @param baseTrademark
     * @return
     */
    @PostMapping("save")
    public Result saveBaseTrademark(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    /**
     * 根据品牌Id,修改品牌信息
     * @param baseTrademark
     * @return
     */
    @PutMapping("update")
    public Result updateBaseTrademark(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    /**
     * 根据品牌Id，删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public Result removeBaseTrademark(@PathVariable Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    /**
     * 获取全部的品牌属性
     * @return
     */
    @GetMapping("getTrademarkList")
    public Result getTrademarkList(){
        return Result.ok(baseTrademarkService.list(null));
    }
}
