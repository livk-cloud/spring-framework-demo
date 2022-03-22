package com.livk.mqtt;

import com.livk.common.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * MqttApp
 * </p>
 *
 * @author livk
 * @date 2022/3/2
 */
@SpringBootApplication
public class MqttApp {
    public static void main(String[] args) {
        LivkSpring.runServlet(MqttApp.class, args);
    }
}
