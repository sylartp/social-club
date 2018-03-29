package com.socialClub.service;

import java.util.Set;

/**
 * Created by peng.tian on 2018/3/22
 */
public interface IRedisService<K,V> {

    boolean set(String key, Object value);

    <V> V get(String key);

    boolean setExpire(String key);

    boolean remove(String key);

    long getExpire(String key);

    long dbSize();

    Set<String> keys(String pattern);
}
