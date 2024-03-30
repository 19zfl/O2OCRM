package com.o2ocrm.system.basic;

import com.o2ocrm.O2OCRMApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName:RedisConnectionTest
 * @Description: redis链接测试
 * @Author:zfl19
 * @CreateDate:2024/3/12 18:08
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = O2OCRMApp.class)
public class RedisConnectionTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void connTest() {
        redisTemplate.opsForValue().set("name",  "刘十三");
    }

}
