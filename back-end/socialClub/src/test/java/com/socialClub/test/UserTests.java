package com.socialClub.test;


import com.socialClub.dao.UserMapper;
import com.socialClub.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by peng.tian on 2018/3/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void selectUser(){
        User user = userMapper.findUserByUsername("tp");
        assertEquals("123456",user.getPassword());
    }
}
