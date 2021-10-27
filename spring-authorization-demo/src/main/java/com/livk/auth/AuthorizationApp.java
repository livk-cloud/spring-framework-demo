package com.livk.auth;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * AuthorizationApp
 * </p>
 *
 * @author livk
 * @date 2021/10/26
 */
@SpringBootApplication
public class AuthorizationApp {
    public static void main(String[] args) {
        LivkSpring.run(AuthorizationApp.class, args);
    }
}
