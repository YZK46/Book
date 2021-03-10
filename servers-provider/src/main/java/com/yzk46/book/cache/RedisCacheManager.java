package com.yzk46.book.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: redis缓存管理器
 * @author: yzk46
 * @create: 2021-03-08 22:15
 **/
@Component
public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println(s);
        return redisCache;
    }
}
