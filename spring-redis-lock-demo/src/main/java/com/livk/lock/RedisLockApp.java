package com.livk.lock;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * RedisLockApp
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@SpringBootApplication
public class RedisLockApp {
    public static void main(String[] args) {
        LivkSpring.run(RedisLockApp.class, args);
    }
}
