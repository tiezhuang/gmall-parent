package com.atguigu.gmall.list.client;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.list.client.impl.ListDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LTZ
 * @create 2020-12-09 19:26
 */
@FeignClient(value = "service-list",fallback = ListDegradeFeignClient.class)
public interface ListFeignClient {
    /**
     * 更新热点数据
     * @param skuId
     * @return
     */
    @GetMapping("api/list/inner/incrHotScore/{skuId}")
    Result incrHotScore(@PathVariable("skuId") Long skuId);
}
