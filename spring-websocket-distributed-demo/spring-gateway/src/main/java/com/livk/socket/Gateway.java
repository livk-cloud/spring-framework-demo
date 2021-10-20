package com.livk.socket;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * Gateway
 * </p>
 *
 * @author livk
 * @date 2021/10/20
 */
@SpringBootApplication
public class Gateway {
    public static void main(String[] args) {
        LivkSpring.run(Gateway.class, args);
    }
}
