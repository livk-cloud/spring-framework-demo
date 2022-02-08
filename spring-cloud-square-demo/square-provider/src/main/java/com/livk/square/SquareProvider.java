package com.livk.square;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * SquareProvider
 * </p>
 *
 * @author livk
 * @date 2021/12/15
 */
@SpringBootApplication
public class SquareProvider {
    public static void main(String[] args) {
        LivkSpring.runServlet(SquareProvider.class, args);
    }
}
