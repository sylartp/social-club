package com.socialClub.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * Created by peng.tian on 2018/3/29
 */
@Configuration
@ConditionalOnClass(value = RedisClusterConfig.class)
public class RedisClusterConfig {

    @Resource
    private RedisClusterProperty property;

    public static StringRedisSerializer keySerializer = new StringRedisSerializer();

    public static JdkSerializationRedisSerializer valueSerializer = new JdkSerializationRedisSerializer();

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration(){
        RedisClusterConfiguration redisClusterConfig = new RedisClusterConfiguration(property.getNodes());
        redisClusterConfig.setMaxRedirects(property.getMaxRedirects());
        return redisClusterConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory(redisClusterConfiguration(), jedisPoolConfig());
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(property.getMaxIdle());
        jedisPoolConfig.setMinIdle(property.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(property.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(property.isTestOnReturn());
        jedisPoolConfig.setMaxWaitMillis(property.getMaxWaitMillis());
        jedisPoolConfig.setMinEvictableIdleTimeMillis(6000);
        jedisPoolConfig.setMaxTotal(1000);
        return jedisPoolConfig;
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setExposeConnection(true);
        return  redisTemplate;
    }

}
