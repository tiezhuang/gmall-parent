package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 罗铁壮
 * @create 2020-11-30 16:06
 */
public interface ManageService {
    /**
     * 查询所有的一级分类信息
     *
     * @return
     */
    List<BaseCategory1> getCategory1();

    /**
     * 根据一级分类，查询二级分类数据
     *
     * @param category1Id
     * @return
     */
    List<BaseCategory2> getCategory2(Long category1Id);

    /**
     * 根据二级分类Id,查询三级分类数据
     *
     * @param category2Id
     * @return
     */
    List<BaseCategory3> getCategory3(Long category2Id);

    /**
     * * 根据分类Id 获取平台属性数据
     * * 接口说明：
     * *      1，平台属性可以挂在一级分类、二级分类和三级分类
     * *      2，查询一级分类下面的平台属性，传：category1Id，0，0；   取出该分类的平台属性
     * *      3，查询二级分类下面的平台属性，传：category1Id，category2Id，0；
     * *         取出对应一级分类下面的平台属性与二级分类对应的平台属性
     * *      4，查询三级分类下面的平台属性，传：category1Id，category2Id，category3Id；
     * *         取出对应一级分类、二级分类与三级分类对应的平台属性
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

    /**
     * 保存平台属性方法
     *
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 选中准修改数据 ， 根据该attrId 去查找AttrInfo，该对象下 List<BaseAttrValue>
     *
     * @param BaseAttrInfo
     * @return
     */
    BaseAttrInfo getAttrInfo(Long attrId);

    /**
     * 分页获取spu
     *
     * @param page
     * @param spuInfo
     * @return
     */
    IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> pagers, SpuInfo spuInfo);

    /**
     * 获取全部销售属性
     *
     * @return
     */
    List<BaseSaleAttr> baseSaleAttrList();

    /**
     * 保存spu信息
     *
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 根据spuId获取spu图片路径以list集合返回
     *
     * @param spuId
     * @return
     */
    List<SpuImage> spuImageList(long spuId);

    /**
     * 根据spuId获取销售属性以list集合返回
     *
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> spuSaleAttrList(long spuId);

    /**
     * 保存sku信息
     *
     * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 分页获取sku信息
     *
     * @param skuInfoPage
     * @return
     */
    IPage<SkuInfo> getSkuInfoList(Page<SkuInfo> skuInfoPage);

    /**
     * 上架sku
     *
     * @param skuId
     */
    void onSales(Long skuId);

    /**
     * 下架sku
     *
     * @param skuId
     */
    void cancelSales(Long skuId);

    /**
     * 根据skuid获取skuInfo基本信息和是skuid的一组照片
     *
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfobyId(long skuId);

    /**
     * 根据category3Id获取各级分类信息
     *
     * @param skuId
     * @return
     */
    BaseCategoryView getBaseCategoryView(long category3Id);

    /**
     * 根据skuId获取每个商品的价格
     *
     * @param skuId
     * @return
     */
    BigDecimal getSkuPrice(long skuId);

    /**
     * 根据skuId和spuId获取SpuSaleAttrList集合里面有商品的销售属性和销售属性值
     *
     * @param skuId
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrListBySkuId(long skuId, long spuId);
    /**
     * 根据spuid获取以map存储的以属性值为Key,skuId为value的map
     * @param spuId
     * @return
     */
    Map getSkuValueIdsMap(long spuId);
}
