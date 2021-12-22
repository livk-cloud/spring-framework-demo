package com.livk.spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.SneakyThrows;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * <p>
 * OpenApiConfig
 * </p>
 *
 * @author livk
 * @date 2021/11/17
 */
@Configuration
public class OpenApiConfig {

    @SneakyThrows
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSecuritySchemes("basicScheme", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic"))
                        .addParameters("header", new Parameter()
                                .in("header")
                                .schema(new StringSchema())
                                .name("header"))
                        .addParameters("myGlobalHeader", new HeaderParameter()
                                .required(true)
                                .name("My-Global-Header")
                                .description("My Global Header")
                                .schema(new StringSchema())
                                .required(false)))
                // 如果配置此项，需要添加跨域
                // .servers(List.of(server))
                .info(new Info()
                        .title("Livk Api Doc")
                        .description("Api Doc")
                        .version("0.0.1 \nSpringBoot Version" + SpringBootVersion.getVersion())
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Livk Full Documentation")
                        .url("https://springdoc.org/"));
    }

}
