package com.livk.square.config;

import okhttp3.OkHttpClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * OkhttpConfig
 * </p>
 *
 * @author livk
 * @date 2021/12/15
 */
@Configuration
public class OkhttpConfig {

    @Bean
    @LoadBalanced
    public OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }
}
