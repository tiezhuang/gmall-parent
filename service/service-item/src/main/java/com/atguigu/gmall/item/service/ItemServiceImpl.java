package com.atguigu.gmall.item.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 罗铁壮
 * @create 2020-12-04 17:02
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public Map<String, Object> getskuInfoByskuId(Long skuId) {
        Map<String, Object> stringObjectMap = new HashMap<>();
        return stringObjectMap;
    }
}
