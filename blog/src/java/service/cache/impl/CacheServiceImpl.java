package service.cache.impl;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import service.cache.CacheService;
import service.common.impl.BaseServiceImpl;

import java.util.Collection;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 15:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@Service
public class CacheServiceImpl extends BaseServiceImpl implements CacheService {


    /*@Cacheable(value="HelloWorldCache",key="#param")
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }*/

}
