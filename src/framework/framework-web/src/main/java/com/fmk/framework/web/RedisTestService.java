package com.fmk.framework.web;

import com.fmk.framework.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTestService implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(RedisTestService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        test();
    }

    public void test(){
//        int i = 0;
//        while (i==0){
//            redisTemplate.opsForValue().set("redis-test", "1");
//            try {
//                Thread.sleep(1000);
//            }catch (Exception e){}
//        }
    }
}
