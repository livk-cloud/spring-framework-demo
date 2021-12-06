package com.livk.r2dbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * PostgresqlProperties
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@ConfigurationProperties("livk.postgresql")
public record PostgresqlProperties(String host, int port, String username, String password, String database) {
}
