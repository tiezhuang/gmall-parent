package com.atguigu.gmall.all.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 罗铁壮
 * @create 2020-12-08 20:05
 */
@Controller
public class IndexController {
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 获取全部分类信息
     * @param request
     * @return
     */
    @GetMapping({"/","index.html"})
    public String index(HttpServletRequest request){
        Result baseCategoryList = productFeignClient.getBaseCategoryList();
        request.setAttribute("list",baseCategoryList.getData());
        return "index/index";
    }
}
