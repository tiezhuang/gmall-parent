package com.atguigu.gmall.common.cache;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.common.constant.RedisConst;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author 罗铁壮
 * 处理环绕通知
 * @create 2020-12-08 16:40
 */
@Component  //可以被扫描
@Aspect  //异常处理
public class GmallCacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    //  这个方法重要的两点：一个是参数，一个是返回值。
    //  在环绕通知为进入方法体内部的时候，无法确定返回值，如果一旦切入到了方法体内部就知道具体的返回值了。
    //切GmallCache注解
    @SneakyThrows
    @Around("@annotation(com.atguigu.gmall.common.cache.GmallCache)")
    public Object cacheAroundAdvice(ProceedingJoinPoint joinPoint) {
        //声明一个对象
        Object object = new Object();
        //  分布式锁的业务逻辑
        //  需要缓存的key，那么这个缓存的key 是由谁来组成的。注解的前缀+方法体的参数组成
        //  需要先找注解，注解在方法上，判断方法上是否有注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        GmallCache gmallCache = signature.getMethod().getAnnotation(GmallCache.class);
        //获取到注解上的前缀
        String prefix = gmallCache.prefix();
        //方法传入的参数
        Object[] args = joinPoint.getArgs();
        //组成缓存的key,需要前缀+方法传入的参数
        String key = prefix + Arrays.asList(args).toString();
        //防止redis,redisson出现问题
        try {
            //  获取缓存中的数据
            //  redisTemplate.opsForValue().get(key); 表示获取缓存的数据 ； 需要将数据转换为方法的返回值类型
            //  methodSignature.getReturnType() 获取方法的返回值类型
            //  skuInfo = (SkuInfo) redisTemplate.opsForValue().get(skuKey);
            object = cacheHit(key,signature);
            //判断缓存中的数据是否为空
            if(object == null){
                //从数据库中获取数据，并放入缓存，防止缓存击穿必须上锁
                // perfix = sku  index1 skuId = 32 , index2 skuId = 33
                //  public SkuInfo getSkuInfo(Long skuId)
                //  key+":lock"
                RLock lock = redissonClient.getLock(key + ":lock");
                boolean flag = lock.tryLock(RedisConst.SKULOCK_EXPIRE_PX1, RedisConst.SKULOCK_EXPIRE_PX2, TimeUnit.SECONDS);
                //判断上锁是否成功
                if (flag){
                    try {
                        //表示上锁成功，查询数据库
                        //执行方法体joinPoint。proceed();如果在方法上能够找到注解，表示要执行方法体，ruturn getSkuInfoDB(skuId)
                        //skuInfo = getSkuInfoDB(skuId);
                        object = joinPoint.proceed(joinPoint.getArgs());

                        //防止缓存穿透
                        if (object == null){
                            Object object1 = new Object();
                            redisTemplate.opsForValue().set(key, JSON.toJSONString(object1),RedisConst.SKUKEY_TEMPORARY_TIMEOUT,TimeUnit.SECONDS);
                            return object1;
                        }
                        redisTemplate.opsForValue().set(key, JSON.toJSONString(object),RedisConst.SKUKEY_TIMEOUT,TimeUnit.SECONDS);
                        return object;
                    } finally {
                            //解锁
                        lock.unlock();
                    }
                }else {
                    //没有获取到锁的线程等待自旋
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //  自旋
                    return cacheAroundAdvice(joinPoint);
                }

            }else {
                return object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  返回对象+数据库兜底
        return joinPoint.proceed(joinPoint.getArgs());
    }

    private Object cacheHit(String key, MethodSignature methodSignature) {
        //获取缓存中的数据
        String strValue = (String) redisTemplate.opsForValue().get(key);
        //判断获取的数据不为空，返回数据+数据类型
        if(!StringUtils.isEmpty(strValue)){
            //数据类型
            //  SkuInfo getSkuInfoById(Long skuId) 返回 SkuInfo
            //  BigDecimal getSkuPrice(Long skuId) 返回 BigDecimal
            Class returnType = methodSignature.getReturnType();
            return JSON.parseObject(strValue,returnType);
        }
        return null;
    }

}
