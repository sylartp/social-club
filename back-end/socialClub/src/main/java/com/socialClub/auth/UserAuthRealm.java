package com.socialClub.auth;

import com.socialClub.dao.PermissionMapper;
import com.socialClub.dao.UserMapper;
import com.socialClub.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by peng.tian on 2018/3/23
 */
@Slf4j
public class UserAuthRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("authority");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String email = (String) principalCollection.getPrimaryPrincipal();
        User user = userMapper.findUserAndRoleByEmail(email);
        user.getRoles().forEach(role -> {
            authorizationInfo.addRole(role.getName());
            permissionMapper.findPermissionByRoleId(role.getId()).forEach(permission -> authorizationInfo.addStringPermission(permission.getName()));
        });
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("authentication");
        String email = (String) token.getPrincipal();
        User user = userMapper.findUserByEmail(email);
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
