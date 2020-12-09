package com.atguigu.gmall.common.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 罗铁壮
 * @create 2020-12-08 16:37
 */
@Target({ElementType.METHOD})  //注解的作用域
@Retention(RetentionPolicy.RUNTIME) //注解的存活范围
public @interface GmallCache {
    /**
     * 缓存key的前缀
     * @return
     */
    String prefix() default "cache";
}
