package com.livk.redis;

import com.livk.common.LivkSpring;
import com.livk.common.SpringContextHolder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * ProviderRedis
 * </p>
 *
 * @author livk
 * @date 2021/11/26
 */
@EnableScheduling
@SpringBootApplication
public class ProviderRedis {
    public static void main(String[] args) {
        LivkSpring.runServlet(ProviderRedis.class, args);
    }
}
