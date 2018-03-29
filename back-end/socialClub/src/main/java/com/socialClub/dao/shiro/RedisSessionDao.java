package com.socialClub.dao.shiro;

import com.socialClub.service.IRedisService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by peng.tian on 2018/3/23
 */
@Slf4j
@Repository
public class RedisSessionDao extends AbstractSessionDAO {

    @Setter
    private IRedisService redisService;
    private final static String SESSION_PREFIX = "shiro_redis_session:";

    private String getKey(Session session){
        return SESSION_PREFIX+session.getId().toString();
    }

    private String getKey(Serializable sessionId) {
        return SESSION_PREFIX+sessionId.toString();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        log.info("action is creating session and sessionId is {}", session.getId());
        redisService.set(getKey(session),session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("action is getting session and sessionId is {}", sessionId);
        return (Session) redisService.get(getKey(sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("action is updating session and sessionId is {}", session.getId().toString());
        redisService.set(getKey(session), session);
    }

    @Override
    public void delete(Session session) {
        redisService.remove(getKey(session));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keySet = redisService.keys(SESSION_PREFIX+"*");
        List<Session> sessionList = keySet.stream().map(key->(Session)redisService.get(key)).collect(Collectors.toList());
        return sessionList;
    }
}

