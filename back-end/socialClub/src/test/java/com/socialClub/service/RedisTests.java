package com.socialClub.service;

import com.socialClub.service.impl.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
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

    @Autowired
    private RedisService redisService;

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

    @Test
    public void serviceTests(){
        redisService.set(KEY_TEST, "123");

        redisService.get(KEY_TEST);
        System.out.println("++++++++++++++++++"+redisService.getExpire(KEY_TEST));
//        redisService.remove(KEY_TEST);
    }

    @Test
    public void loginTest(){
        UsernamePasswordToken token = new UsernamePasswordToken("tp","123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Assert.assertThat(true, CoreMatchers.equalTo(subject.isAuthenticated()));
    }
}
