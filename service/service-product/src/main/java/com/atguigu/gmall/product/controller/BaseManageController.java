package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.ManageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-11-30 16:41
 */
@Api(tags = "商品基础属性接口")
@RestController
@CrossOrigin
@RequestMapping("admin/product")
public class BaseManageController {
    @Autowired
    private ManageService manageService;

    /**
     * 获取一级分类所有信息
     * @return
     */
    @GetMapping("getCategory1")
    public Result<List<BaseCategory1>> getCategory1(){
        return Result.ok(manageService.getCategory1());
    }

    /**
     * 根据一级分类的Id 获取二级分类所有信息
     * @param category1Id
     * @return
     */
    @GetMapping("getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable("category1Id") Long category1Id){
        return Result.ok(manageService.getCategory2(category1Id));
    }


    /**
     * 根据二级目录Id 获取三级目录的所有信息
     * @param category2Id
     * @return
     */
    @GetMapping("getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable("category2Id") Long category2Id){
        return Result.ok(manageService.getCategory3(category2Id));
    }
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(@PathVariable("category1Id") Long category1Id,
                                                   @PathVariable("category2Id") Long category2Id,
                                                   @PathVariable("category3Id") Long category3Id){
        return Result.ok(manageService.getAttrInfoList(category1Id,category2Id,category3Id));
    }

    /**
     * 保存平台属性方法
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo); // 前台数据都被封装到该对象中baseAttrInfo
        return Result.ok();
    }

    /**
     * 根据属性Id 获取属性,然后在获取属性中的属性值数据集合
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result<List<BaseAttrValue>> getAttrValueList(@PathVariable("attrId") Long attrId){
        return Result.ok(manageService.getAttrInfo(attrId).getAttrValueList());
    }
}
