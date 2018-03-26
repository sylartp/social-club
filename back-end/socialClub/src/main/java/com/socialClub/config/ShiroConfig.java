package com.socialClub.config;

import com.socialClub.auth.ShiroRedisCacheManager;
import com.socialClub.auth.UserAuthRealm;
import com.socialClub.dao.shiro.RedisSessionDao;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by peng.tian on 2018/3/22
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userAuthRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new RedisSessionDao();
    }

    @Bean
    public CacheManager cacheManager(){
        return new ShiroRedisCacheManager();
    }

    @Bean
    public UserAuthRealm userAuthRealm(){
        UserAuthRealm userAuthRealm = new UserAuthRealm();
        userAuthRealm.setCredentialsMatcher(new PasswordMatcher());
        return userAuthRealm;
    }

//    @Bean
//    public PasswordMatcher passwordMatcher(){
//        PasswordMatcher passwordMatcher = new PasswordMatcher();
//        passwordMatcher.doCredentialsMatch()
//        passwordMatcher.setPasswordService(new DefaultPasswordService());
//        return passwordMatcher;
//    }
}
