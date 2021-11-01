package com.livk.auth;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * AuthorizationServer
 * </p>
 *
 * @author livk
 * @date 2021/10/27
 */
@SpringBootApplication
public class AuthorizationServer {
    public static void main(String[] args) {
        LivkSpring.run(AuthorizationServer.class, args);
    }
}
