package com.atguigu.gmall.product.service;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 罗铁壮
 * @create 2020-12-01 16:59
 */
public interface BaseTrademarkService extends IService<BaseTrademark> {
    /**
     * 分页获取商业品牌
     * @param page
     * @return
     */
    IPage<BaseTrademark> selectPage(Page<BaseTrademark> page);
}
