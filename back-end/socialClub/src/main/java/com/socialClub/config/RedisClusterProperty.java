package com.socialClub.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by peng.tian on 2018/3/29
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterProperty {

    private List<String> nodes;

    private int minIdle;

    private int maxIdle;

    private int timeout;

    private int maxRedirects;

    private long maxWaitMillis;

    private boolean testOnReturn;

    private boolean testOnBorrow;
}
