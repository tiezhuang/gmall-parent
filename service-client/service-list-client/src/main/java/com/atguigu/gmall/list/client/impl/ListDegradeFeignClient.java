package com.atguigu.gmall.list.client.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.list.client.ListFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author LTZ
 * @create 2020-12-09 19:29
 */
@Component
public class ListDegradeFeignClient implements ListFeignClient {
    @Override
    public Result incrHotScore(Long skuId) {

        return null;
    }
}
