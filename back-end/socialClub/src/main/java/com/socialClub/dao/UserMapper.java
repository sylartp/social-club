package com.socialClub.dao;

import com.socialClub.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peng.tian on 2018/3/1
 */
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findUserByUsername(final String username);


    @Select("select role from user_role_rel where user_id = (select id from user where username = #{username})")
    List<String> findRoleByUsername(final String username);
}
