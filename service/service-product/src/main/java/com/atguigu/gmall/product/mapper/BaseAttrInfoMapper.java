package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-11-30 16:03
 */
@Mapper
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {
    /**
     * 根据分类Id 查询平台属性集合对象 | 编写xml 文件
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> selectBaseAttrInfoList(@Param("category1Id") Long category1Id,
                                              @Param("category2Id")Long category2Id,
                                              @Param("category3Id")Long category3Id);

}
