package com.atguigu.gmall.list.repository;

import com.atguigu.gmall.model.list.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author LTZ
 * @create 2020-12-09 18:05
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
