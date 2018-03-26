package com.socialClub.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by peng.tian on 2018/3/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    private static final String KEY_TEST = "key:test";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void baseTest(){
        stringRedisTemplate.opsForValue().set(KEY_TEST, "Hello,Redis!");
        Assert.assertEquals("Hello,Redis!", stringRedisTemplate.opsForValue().get(KEY_TEST));
        stringRedisTemplate.delete(KEY_TEST);
    }

    @Test
    public void execTest(){
       boolean result = stringRedisTemplate.execute(new RedisCallback<Boolean>() {

           @Override
           public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
               RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
               redisConnection.set(serializer.serialize("abc"), serializer.serialize("123"));
               return true;
           }
       });
        System.out.println(stringRedisTemplate.opsForValue().get("abc"));
    }
}
