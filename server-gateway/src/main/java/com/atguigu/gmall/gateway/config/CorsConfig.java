package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author 罗铁壮
 * @create 2020-12-01 16:02
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){
        //cors跨域配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置允许访问的网络
        corsConfiguration.setAllowCredentials(true);//设置是否从服务器获取cookie
        corsConfiguration.addAllowedMethod("*");//设置请求方法 * 表示任意
        corsConfiguration.addAllowedHeader("*");//所有请求信息 * 表示任意
        //配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        //cors过滤对象
        return new CorsWebFilter(configurationSource);

    }
}
