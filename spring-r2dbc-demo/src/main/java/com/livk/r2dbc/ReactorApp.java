package com.livk.r2dbc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * <p>
 * ReactorApp
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@EnableWebFlux
@SpringBootApplication
public class ReactorApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ReactorApp.class).web(WebApplicationType.REACTIVE).run(args);
    }
}
