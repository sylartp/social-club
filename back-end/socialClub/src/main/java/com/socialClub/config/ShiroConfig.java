package com.socialClub.config;

import com.google.common.collect.Maps;
import com.socialClub.auth.ShiroRedisCacheManager;
import com.socialClub.auth.UserAuthRealm;
import com.socialClub.dao.shiro.RedisSessionDao;
import com.socialClub.service.IRedisService;
import com.socialClub.service.impl.RedisClusterService;
import com.socialClub.service.impl.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by peng.tian on 2018/3/22
 */
@Configuration
@Order(1)
@Slf4j
public class ShiroConfig {

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userAuthRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setCacheManager(cacheManager());
        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO(){
        RedisSessionDao sessionDAO = new RedisSessionDao();
        sessionDAO.setRedisService(redisService());
        return sessionDAO;
    }

    @Bean(name = "shrioRedisCacheManager")
    public CacheManager cacheManager(){
        ShiroRedisCacheManager cacheManager = new ShiroRedisCacheManager();
        cacheManager.setRedisService(redisService());
        return cacheManager;
    }

    @Bean
    @DependsOn(value={"lifecycleBeanPostProcessor", "shrioRedisCacheManager"})
    public UserAuthRealm userAuthRealm(){
        UserAuthRealm userAuthRealm = new UserAuthRealm();
        userAuthRealm.setCredentialsMatcher(new SimpleCredentialsMatcher());
        userAuthRealm.setCacheManager(cacheManager());
        userAuthRealm.setCachingEnabled(true);
        userAuthRealm.setAuthorizationCachingEnabled(true);
        return userAuthRealm;
    }

    @Bean
    public IRedisService redisService(){
        return new RedisClusterService();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
        filterMap.put("authc", new UserFilter());
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/unauthor");
        Map<String, String> chains = Maps.newHashMap();
        chains.put("/login", "authc");
        chains.put("/get", "perms");
        chains.put("/admin","roles[admin]");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        log.info("ShiroConfiguration.lifecycleBeanPostProcessor()");
        return new LifecycleBeanPostProcessor();
    }

    @ConditionalOnMissingBean
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        log.info("ShiroConfiguration.getDefaultAdvisorAutoProxyCreator()");
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager());
        return aASA;
    }

}
