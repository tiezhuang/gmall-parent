package com.atguigu.gmall.list.service;

/**
 * @author LTZ
 * @create 2020-12-09 17:50
 */
public interface SearchService {
    /**
     * 上架商品列表
     * @param skuId
     */
    void upperGoods(Long skuId);

    /**
     * 下架商品列表
     * @param skuId
     */
    void lowerGoods(long skuId);

    /**
     * 更新热点
     * @param skuId
     */
    void incrHotScore(Long skuId);
}
