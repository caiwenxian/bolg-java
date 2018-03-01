package utils;


import model.po.user.UserPO;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.security.auth.kerberos.KerberosTicket;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-02-26 17:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@Service
public class CacheUtil {

    CacheManager manager;

    public CacheManager getManager() {
        return manager;
    }

    public void setManager(CacheManager manager) {
        this.manager = manager;
    }

    public void put(Object cacheType, Object key, Object value) {
        Cache cache = manager.getCache(String.valueOf(cacheType));
        cache.put(key, value);
    }

    public <TARGET>TARGET get(Object cacheType, String key, Class<TARGET> target) {
        Cache cache = manager.getCache(String.valueOf(cacheType));
//        Element element = cache.get(key);
//        return element.getValue();

        return cache.get(key, target);
    }
}
