package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;

import java.util.List;

/**
 * @author 罗铁壮
 * @create 2020-11-30 16:06
 */
public interface ManageService {
    /**
     * 查询所有的一级分类信息
     * @return
     */
    List<BaseCategory1> getCategory1();

    /**
     * 根据一级分类，查询二级分类数据
     * @param category1Id
     * @return
     */
    List<BaseCategory2> getCategory2(Long category1Id);

    /**
     * 根据二级分类Id,查询三级分类数据
     * @param category2Id
     * @return
     */
    List<BaseCategory3> getCategory3(Long category2Id);

    /**
     *  * 根据分类Id 获取平台属性数据
     *  * 接口说明：
     *  *      1，平台属性可以挂在一级分类、二级分类和三级分类
     *  *      2，查询一级分类下面的平台属性，传：category1Id，0，0；   取出该分类的平台属性
     *  *      3，查询二级分类下面的平台属性，传：category1Id，category2Id，0；
     *  *         取出对应一级分类下面的平台属性与二级分类对应的平台属性
     *  *      4，查询三级分类下面的平台属性，传：category1Id，category2Id，category3Id；
     *  *         取出对应一级分类、二级分类与三级分类对应的平台属性
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> getAttrInfoList(Long category1Id,Long category2Id,Long category3Id);

    /**
     * 保存平台属性方法
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 选中准修改数据 ， 根据该attrId 去查找AttrInfo，该对象下 List<BaseAttrValue>
     * @param BaseAttrInfo
     * @return
     */
    BaseAttrInfo getAttrInfo(Long attrId);
}
