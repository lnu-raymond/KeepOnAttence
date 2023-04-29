package com.leevan;

import com.leevan.Utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @projectName:    KeepOnAttence 
 * @package:        com.leevan
 * @className:      RedisTest
 * @author:     冯莉文
 * @description:  TODO  
 * @date:    2023/3/27 10:10
 * @version:    1.0
 */ 
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads()
    {
        String key1 = "kkk";
        redisUtil.set(key1,"hello world");
        Object value = redisUtil.get(key1);
        System.out.println(value);
    }
}