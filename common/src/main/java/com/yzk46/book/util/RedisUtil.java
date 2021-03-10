package com.yzk46.book.util;

import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: book
 * @description: 获取redis示例的工具类
 * @author: yzk46
 * @create: 2021-03-08 22:40
 **/
@Component
public class RedisUtil<T> {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,T> redisTemplate;


    /**
     * 获取字符串类型缓存
     * @param key redis中的key
     * @return redis 的 value
     */
    public  String  getString(String key){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 存入字符串类型数据
     * @param key redis key
     * @param value redis value
     */
    public boolean setString(String key,String value){
        return setString(key,value,0);
    }

    /**
     * 存入字符串类型数据，设置存储时间
     * @param key key
     * @param value value
     * @param time 存放时间
     */
    public boolean setString(String key,String value,long time){
        if(StringUtils.isEmpty(key)||StringUtils.isEmpty(value)){
            return false;
        }
        if(time==0){
            stringRedisTemplate.opsForValue().set(key,value);
        }else{
            stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     *  获取存储的对象
     * @param key key
     * @return 对象
     */
    public T getObject(String key){
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 存入对象类型数据
     * @param key redis key
     * @param value redis value
     */
    public boolean setObject(String key,T value){
        return setObject(key,value,0);
    }

    /**
     * 存入对象类型数据，设置存储时间
     * @param key key
     * @param value value
     * @param time 存放时间
     */
    public boolean setObject(String key,T value,long time){
        if(StringUtils.isEmpty(key)||value==null){
            return false;
        }
        if(time==0){
            redisTemplate.opsForValue().set(key,value);
        }else{
            redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 重新设置对象失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean addObjInvalidTime(String key,long time){
        if(StringUtils.isEmpty(key)&& redisTemplate.getExpire(key)<=0){
            return false;
        }
        return redisTemplate.expire(key,time,TimeUnit.SECONDS);

    }

    /**
     * 重新设置字符串失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean addStrInvalidTime(String key,long time){
        if(StringUtils.isEmpty(key)&& stringRedisTemplate.getExpire(key)<=0){
            return false;
        }
        return stringRedisTemplate.expire(key,time,TimeUnit.SECONDS);

    }

    /**
     * 根据key 清除缓存
     * @param key  key
     * @return
     */
    public boolean removeObj(String key){
        if(StringUtils.isEmpty(key)){
            return false;
        }
        redisTemplate.delete(key);
        return true;
    }

    /**
     * 根据key 清除缓存
     * @param key  key
     * @return
     */
    public boolean removeStr(String key){
        if(StringUtils.isEmpty(key)){
            return false;
        }
        stringRedisTemplate.delete(key);
        return true;
    }

    public Object hget(String hkey, String dataKey) {
        return stringRedisTemplate.opsForHash().get(hkey, dataKey);
    }

    public void hset(String hkey, String dataKey,String value) {
        stringRedisTemplate.opsForHash().put(hkey,dataKey,value);
    }


}
