package com.livk.r2dbc.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * <p>
 * R2dbcConfig
 * //        return ConnectionFactories.get(
 * //                ConnectionFactoryOptions.builder()
 * //                        .option(DRIVER, "postgresql")
 * //                        .option(HOST, "livk.com")
 * //                        .option(PORT, 5432)
 * //                        .option(USER, "postgres")
 * //                        .option(PASSWORD, "123456")
 * //                        .option(DATABASE, "spring_livk")
 * //                        .option(MAX_SIZE, 40)
 * //                        .build());
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@Configuration
@EnableConfigurationProperties(PostgresqlProperties.class)
public class R2dbcConfig {

    @Bean
    public ConnectionFactory connectionFactory(PostgresqlProperties postgresqlProperties) {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(postgresqlProperties.host())
                .port(postgresqlProperties.port())
                .username(postgresqlProperties.username())
                .password(postgresqlProperties.password())
                .database(postgresqlProperties.database())
                .connectTimeout(Duration.ofSeconds(3)).build());
    }
}
