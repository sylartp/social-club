package com.socialClub.service.impl;

import com.socialClub.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by peng.tian on 2018/3/22
 */
@Service("redisService")
public class RedisService implements IRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean set(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public String get(String key) {
        return Optional.ofNullable(stringRedisTemplate.opsForValue().get(key)).orElse(null);
    }

    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }
}
