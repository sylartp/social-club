package com.socialClub.dao;

import com.socialClub.domain.Permission;
import com.socialClub.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by peng.tian on 2018/3/1
 */

@Repository
public interface UserMapper {

    @Select("select * from user where email = #{email}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "id", property = "roles", many = @Many(select = "com.socialClub.dao.RoleMapper.findRolesByUserId", fetchType = FetchType.LAZY))
    })
    User findUserAndRoleByEmail(final String email);

    @Select("select * from user where email = #{email}")
    User findUserByEmail(final String email);


}
