package com.atguigu.gmall.item.service;

import java.util.Map;

/**
 * @author 罗铁壮
 * @create 2020-12-04 16:22
 */
public interface ItemService {
    /**
     * 根据skuid获取sku商品的全部信息
     * @param skuId
     * @return
     */
    Map<String,Object> getskuInfoByskuId(Long skuId);
}
