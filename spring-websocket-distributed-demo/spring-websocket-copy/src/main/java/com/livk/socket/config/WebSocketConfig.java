package com.livk.socket.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <p>
 * WebSocketConfig
 * </p>
 *
 * @author livk
 * @date 2021/10/20
 */
@SpringBootConfiguration
public class WebSocketConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public WebSocketServer webSocketServer() {
		return new WebSocketServer();
	}

}
