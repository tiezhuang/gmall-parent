package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-11-30 16:13
 */
@Service
public class ManageServiceImpl implements ManageService {
   @Autowired
   private BaseCategory1Mapper baseCategory1Mapper;
   @Autowired
   private BaseCategory2Mapper baseCategory2Mapper;
   @Autowired
   private BaseCategory3Mapper baseCategory3Mapper;
   @Autowired
   private BaseAttrInfoMapper baseAttrInfoMapper;
   @Autowired
   private BaseAttrValueMapper baseAttrValueMapper;
   @Autowired
   private SpuInfoMapper spuInfoMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Override
    public List<BaseCategory1> getCategory1() {
        return baseCategory1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        // select * from baseCategory2 where Category1Id = ?
        return baseCategory2Mapper.selectList(new QueryWrapper<BaseCategory2>().eq("category1_id",category1Id));
    }

    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        return baseCategory3Mapper.selectList(new QueryWrapper<BaseCategory3>().eq("category2_id",category2Id));
    }

    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 什么情况下 是添加，什么情况下是更新，修改 根据baseAttrInfo 的Id
        // baseAttrInfo
        if (baseAttrInfo.getId() !=null){
            // 修改数据
            baseAttrInfoMapper.updateById(baseAttrInfo);
        }else {
            // 新增
            // baseAttrInfo 插入数据
            baseAttrInfoMapper.insert(baseAttrInfo);
        }
        // baseAttrValue 平台属性值
        // 修改：通过先删除{baseAttrValue}，在新增的方式！
        // 删除条件：baseAttrValue.attrId = baseAttrInfo.id
        baseAttrValueMapper.delete(new QueryWrapper<BaseAttrValue>().eq("attr_id",baseAttrInfo.getId()));
        // 获取页面传递过来的所有平台属性值数据
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList !=null && attrValueList.size()>0){
            //循环遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //获取平台属性Id 给attrId
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }
    }

    @Override
    public BaseAttrInfo getAttrInfo(Long attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);
        baseAttrInfo.setAttrValueList(getAttrValueList(attrId));
        return baseAttrInfo;
    }

    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> pagers, SpuInfo spuInfo) {
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<SpuInfo>().eq("category3_id", spuInfo.getCategory3Id()).orderByDesc("id");
        return spuInfoMapper.selectPage(pagers,queryWrapper);
    }

    /**
     * 根据属性Id获取属性值集合
     * @param attrId
     * @return
     */
    public List<BaseAttrValue> getAttrValueList(Long attrId){
        return baseAttrValueMapper.selectList(new QueryWrapper<BaseAttrValue>().eq("attr_id",attrId));
    }
    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectList(null);
    }
}
