package com.livk.job;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * JobApp
 * </p>
 *
 * @author livk
 * @date 2021/10/25
 */
@SpringBootApplication
public class JobApp {
    public static void main(String[] args) {
        LivkSpring.run(JobApp.class, args);
    }
}
