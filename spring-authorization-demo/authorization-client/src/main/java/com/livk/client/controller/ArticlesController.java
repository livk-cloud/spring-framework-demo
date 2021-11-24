package com.livk.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * <p>
 * ArticlesController
 * </p>
 *
 * @author livk
 * @date 2021/10/27
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticlesController {

	@Value("${resources.article-uri}")
	private String resourceBaseUri;

	private final WebClient webClient;

	@GetMapping(value = "/articles", params = "grant_type=authorization_code")
	public String[] getArticles(
			@RegisteredOAuth2AuthorizedClient("articles-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {

		return this.webClient.get().uri(resourceBaseUri)
				.attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
				.retrieve().bodyToMono(String[].class).block();
	}

	@GetMapping(value = "/articles", params = "grant_type=client_credentials")
	public String[] getArticles() {

		return this.webClient.get().uri(this.resourceBaseUri)
				.attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
						.clientRegistrationId("articles-client-client-credentials"))
				.retrieve().bodyToMono(String[].class).block();
	}

}
