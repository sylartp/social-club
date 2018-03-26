package com.socialClub.service;

/**
 * Created by peng.tian on 2018/3/22
 */
public interface IRedisService {

    boolean set(String key, String value);

    String get(String key);

    boolean expire(String key, long expire);
}
