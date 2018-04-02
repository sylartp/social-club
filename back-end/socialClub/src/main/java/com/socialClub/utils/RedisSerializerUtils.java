package com.socialClub.utils;

import static com.socialClub.config.RedisClusterConfig.*;

/**
 * Created by peng.tian on 2018/3/29
 */
public class RedisSerializerUtils {

    public static byte[] stringSerialize(String s){
        return keySerializer.serialize(s);
    }

    public static String stringDeserialize(byte[] bytes){
        return keySerializer.deserialize(bytes);
    }

    public static byte[] objectSerialize(Object o){
        return valueSerializer.serialize(o);
    }

    public static Object objectDeserialize(byte[] bytes){
        return valueSerializer.deserialize(bytes);
    }
}
