package com.socialClub.service.impl;

import com.socialClub.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisCommands;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisCommand;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.socialClub.utils.RedisSerializerUtils.*;

/**
 * Created by peng.tian on 2018/3/29
 */
@Service("redisClusterService")
public class RedisClusterService implements IRedisService {

    @Value("${shiro.redis.expire}")
    private Long expire;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Override
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Object get(String key) {
        jedisConnectionFactory.getClusterConnection();
        return redisTemplate.opsForValue().get(key);
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
        return redisTemplate.execute(RedisCommands::dbSize);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
