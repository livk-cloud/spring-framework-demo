package com.livk.provider.biz;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * ProviderApp
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@SpringBootApplication
public class ProviderApp {
    public static void main(String[] args) {
        LivkSpring.run(ProviderApp.class, args);
    }
}
