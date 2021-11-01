// package com.livk.socket.config;
//
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.cloud.gateway.filter.GatewayFilterChain;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
// import org.springframework.core.Ordered;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;
// import org.springframework.web.util.UriComponentsBuilder;
// import reactor.core.publisher.Mono;
//
// import java.net.URI;
//
/// **
// * <p>
// * WebSocketFilter
// * </p>
// *
// * @author livk
// * @date 2021/10/20
// */
// @Slf4j
// @Component
// public class WebSocketFilter implements GlobalFilter, Ordered {
// @Override
// public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
// URI requiredUrl =
// exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
// String scheme = requiredUrl.getScheme();
// if (!"ws".equals(scheme) && !"wss".equals(scheme)) {
// return chain.filter(exchange);
// }
// String wsScheme = this.convertWsToHttp(scheme);
// URI wsRequestUrl =
// UriComponentsBuilder.fromUri(requiredUrl).scheme(wsScheme).build().toUri();
// exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR,
// wsRequestUrl);
// return chain.filter(exchange);
// }
//
// @Override
// public int getOrder() {
// return Ordered.LOWEST_PRECEDENCE - 2;
// }
//
// private String convertWsToHttp(String scheme) {
// scheme = scheme.toLowerCase();
// return "ws".equals(scheme) ? "http" : "wss".equals(scheme) ? "https" : scheme;
// }
// }
