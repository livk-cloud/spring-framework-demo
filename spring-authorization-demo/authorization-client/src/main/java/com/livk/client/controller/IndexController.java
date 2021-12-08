package com.livk.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * <p>
 * IndexController
 * </p>
 *
 * @author livk
 * @date 2021/10/27
 */
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final WebClient webClient;

    @GetMapping({"/", "/index"})
    public String index() {
        return "<ul>"
               + "<li><a target=\"_blank\" href=\"/articles?grant_type=authorization_code\">访问资源服务的/articles(授权码方式认证)</a></li>"
               + "<li><a target=\"_blank\" href=\"/articles?grant_type=client_credentials\">访问资源服务的/articles(客户端方式认证)</a></li>"
               + "</ul>";
    }
}
