package com.socialClub.dao;

import com.socialClub.domain.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by peng.tian on 2018/3/26
 */
@Repository
public interface PermissionMapper {



    @Select("select * from permission where id in (select permission_id from role_permission_rel where role_id = #{roleId})")
    Set<Permission> findPermissionByRoleId(final int roleId);
}
