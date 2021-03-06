package com.socialClub.service.impl;

import com.socialClub.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.socialClub.utils.RedisSerializerUtils.*;

/**
 * Created by peng.tian on 2018/3/22
 */
@Service("redisService")
public class RedisService implements IRedisService {

    @Value("${shiro.redis.expire}")
    private Long expire;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean set(String key, Object value) {
        try {
            return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
                redisConnection.set(stringSerialize(key), objectSerialize(value), Expiration.from(expire, TimeUnit.MINUTES), RedisStringCommands.SetOption.UPSERT);
                redisConnection.close();
                return true;
            });
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Object get(String key) {
        return redisTemplate.execute((RedisCallback<Object>) redisConnection -> objectDeserialize(redisConnection.get(stringSerialize(key))));
    }

    @Override
    public boolean setExpire(String key) {
        return redisTemplate.expire(key, expire, TimeUnit.MINUTES);
    }

    @Override
    public boolean remove(String key) {
        redisTemplate.delete(key);
        return true;
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MINUTES);
    }

    @Override
    public long dbSize() {
        return redisTemplate.execute(RedisServerCommands::dbSize);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
