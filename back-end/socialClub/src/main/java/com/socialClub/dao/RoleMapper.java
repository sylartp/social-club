package com.socialClub.dao;

import com.socialClub.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by peng.tian on 2018/3/26
 */
@Repository
public interface RoleMapper {


    @Select("select * from role where id in (select role_id from user_role_rel where user_id = #{userId})")
    Set<Role> findRolesByUserId(final int userId);

    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id =true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "permissions", many = @Many(select = "com.socialClub.dao.PermissionMapper.findPermissionByRoleId",fetchType = FetchType.EAGER))
    })
    Role findRoleByRoleId(final int roleId);
}
