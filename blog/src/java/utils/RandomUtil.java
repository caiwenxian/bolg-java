package utils;

import java.util.UUID;

/**
 * 随机码获取工具
 * @Author: [caiwenxian]
 * @Date: [2018-01-19 09:43]
 * @Version: [1.0.0]
 */
public class RandomUtil {

    static public String getUid() {
        return System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
    }

}
