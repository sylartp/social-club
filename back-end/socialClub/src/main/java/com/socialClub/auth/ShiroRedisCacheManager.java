package com.socialClub.auth;

import com.socialClub.service.IRedisService;
import lombok.Setter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by peng.tian on 2018/3/26
 */
@Repository
public class ShiroRedisCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> redisCacheMap = new ConcurrentHashMap<>();
    @Setter
    private IRedisService redisService;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = redisCacheMap.get(name);
        if(cache == null) {
            cache = new RedisCache(redisService);
            redisCacheMap.put(name, cache);
        }
        return cache;
    }
}
