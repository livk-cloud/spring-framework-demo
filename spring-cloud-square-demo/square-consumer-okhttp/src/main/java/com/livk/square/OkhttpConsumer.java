package com.livk.square;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * OkhttpConsumer
 * </p>
 *
 * @author livk
 * @date 2021/12/15
 */
@SpringBootApplication
public class OkhttpConsumer {
    public static void main(String[] args) {
        LivkSpring.runServlet(OkhttpConsumer.class, args);
    }
}
