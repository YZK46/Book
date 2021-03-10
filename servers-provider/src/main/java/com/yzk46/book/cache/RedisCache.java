package com.yzk46.book.cache;

import com.yzk46.book.util.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @program: book
 * @description: redis缓存类
 * @author: yzk46
 * @create: 2021-03-08 22:09
 **/
@Component
public class RedisCache<K,V> implements Cache<K , V> {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public V get(K k) throws CacheException {
        System.out.println("get Key: "+k);
        V v = (V) redisUtil.getObject(k.toString());
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("put key: "+k);
        System.out.println("put value: "+v);
        redisUtil.setObject(k.toString(),v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
