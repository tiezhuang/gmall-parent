package com.atguigu.gmall.product.api;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-12-04 16:57
 */
@RestController
@RequestMapping("api/product")
public class ProductApiController {
    @Autowired
    private ManageService manageService;

    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfor(long skuId) {
        SkuInfo skuInfobyId = manageService.getSkuInfobyId(skuId);
        return skuInfobyId;
    }

    /**
     * 根据三级分类Id查找分类信息
     *
     * @param category3Id
     * @return
     */
    @GetMapping("inner/getCategoryView/{category3Id}")
    public BaseCategoryView getBaseCategoryView(long category3Id) {
        return manageService.getBaseCategoryView(category3Id);
    }

    /**
     * 根据skuId获取每个商品的价格
     *
     * @param skuId
     * @return
     */
    @GetMapping("inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(long skuId) {
        return manageService.getSkuPrice(skuId);
    }

    /**
     * 根据skuId和spuId获取spuSaleAttr集合，里面是商品的销售属性和销售值
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListBySkuId(long skuId,long spuId){
        return manageService.getSpuSaleAttrListBySkuId(skuId,spuId);
    }
}
