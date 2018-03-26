package com.socialClub.test;


import com.socialClub.dao.PermissionMapper;
import com.socialClub.dao.RoleMapper;
import com.socialClub.dao.UserMapper;
import com.socialClub.domain.Permission;
import com.socialClub.domain.Role;
import com.socialClub.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by peng.tian on 2018/3/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.socialClub.dao")
public class UserTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void selectUser(){
        User user = userMapper.findUserAndRoleByEmail("tppppp4free@gmail.com");
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> role.setPermissions(permissionMapper.findPermissionByRoleId(role.getId())));
//                roleMapper.findRoleByRoleId(role.getId()));
//        roles.forEach(role -> role = roleMapper.findRoleByRoleId(role.getId()));
        assertEquals("123456",user.getPassword());
    }
}
