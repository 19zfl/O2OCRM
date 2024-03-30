package com.o2ocrm.system.utils.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @ClassName:RedisServiceCheck
 * @Description: redis服务运行检测
 * @Author:zfl19
 * @CreateDate:2024/3/31 1:54
 */

@Slf4j
public class RedisServiceCheck {

    public static Boolean check() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        try {
            // 尝试ping Redis服务器
            String pingResponse = jedis.ping();
            if ("PONG".equals(pingResponse)) {
                log.info("Redis服务运行正常.");
                return true;
            } else {
                log.debug("无法确认Redis服务状态，收到的响应: " + pingResponse);
                return false;
            }
        } catch (Exception e) {
            log.error("无法连接到Redis服务: " + e.getMessage());
            return false;
        } finally {
            // 关闭连接
            jedis.close();
        }
    }

}
