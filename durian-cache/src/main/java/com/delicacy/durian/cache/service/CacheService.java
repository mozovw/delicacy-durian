package com.delicacy.durian.cache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author yutao
 * @create 2018-09-11 17:12
 **/
@Component
@CacheConfig(cacheNames = "cacheService")
public class CacheService {


    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public String uuid(String uuid) {
        long currentTimeMillis = System.currentTimeMillis();
        return uuid + "_" + currentTimeMillis;
    }

}
