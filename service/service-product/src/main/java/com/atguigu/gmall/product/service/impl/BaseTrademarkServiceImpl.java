package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

/**
 * @author 罗铁壮
 * @create 2020-12-01 17:17
 */
@Service
public class BaseTrademarkServiceImpl extends  ServiceImpl<BaseTrademarkMapper, BaseTrademark> implements BaseTrademarkService {
   @Autowired
   private BaseTrademarkMapper baseTrademarkMapper;
    @Override
    public IPage<BaseTrademark> selectPage(Page<BaseTrademark> page) {

        return baseTrademarkMapper.selectPage(page,new QueryWrapper<BaseTrademark>().orderByAsc("id"));
    }
}
