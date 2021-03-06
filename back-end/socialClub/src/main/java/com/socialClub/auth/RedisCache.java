package com.socialClub.auth;

import com.socialClub.service.IRedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

/**
 * Created by peng.tian on 2018/3/26
 *
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private IRedisService redisService;

    private final static String CACHE_PREFIX = "shiro_redis:cache:";

    RedisCache(IRedisService redisService){
        this.redisService = redisService;
    }

    @Override
    public V get(K key) throws CacheException {
        return (V) redisService.get(CACHE_PREFIX+String.valueOf(key));
    }

    @Override
    public V put(K key, V value) throws CacheException {
        redisService.set(CACHE_PREFIX+String.valueOf(key), value);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {

        redisService.remove(CACHE_PREFIX+String.valueOf(key));
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return (int) redisService.dbSize();
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
