package com.livk.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetAddress;

@Slf4j
public class LivkSpring {

    private static final String HTTP_PREFIX = "IP Address: http";

    private LivkSpring() {
    }

    public static <T> ConfigurableApplicationContext runServlet(Class<T> targetClass, String[] args) {
        return LivkSpring.run(targetClass, args, WebApplicationType.SERVLET);
    }

    public static <T> ConfigurableApplicationContext runReactive(Class<T> targetClass, String[] args) {
        return LivkSpring.run(targetClass, args, WebApplicationType.REACTIVE);
    }

    @SneakyThrows
    private static <T> ConfigurableApplicationContext run(Class<T> targetClass, String[] args, WebApplicationType webApplicationType) {
        var context = new SpringApplicationBuilder(targetClass)
                .web(webApplicationType)
                .banner(LivkBanner.create())
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
        new Thread(() -> print(context, targetClass), InetAddress.getLocalHost().getHostAddress()).start();
        return context;
    }

    @SneakyThrows
    private static <T> void print(ApplicationContext context, Class<T> targetClass) {
        var logger = LoggerFactory.getLogger(targetClass);
        var port = context.getEnvironment().getProperty("server.port");
        logger.info(HTTP_PREFIX.concat("://{}:{}"), InetAddress.getLocalHost().getHostAddress(),
                port == null ? "8080" : port);
    }

}
