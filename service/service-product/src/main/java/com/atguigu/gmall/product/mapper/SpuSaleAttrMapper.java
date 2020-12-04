package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-12-02 16:50
 */
@Mapper
public interface  SpuSaleAttrMapper extends BaseMapper<SpuSaleAttr> {
    /**
     * 根据spuId获取销售属性以list集合返回
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSpuSaleAttrList(long spuId);
}
