package com.livk.client.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.StringUtils;
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
@RequiredArgsConstructor
public class ArticlesController {

    @Value("${resources.article-uri}")
    private String resourceBaseUri;

    private final WebClient webClient;

    /**
     * '/authorized' 是授权码模式注册的 'redirect_uri'
     *
     * @param request request
     * @return string
     */
    @GetMapping(value = "/authorized", params = OAuth2ParameterNames.ERROR)
    public String authorizationFailed(HttpServletRequest request) {
        String errorCode = request.getParameter(OAuth2ParameterNames.ERROR);
        String errorText = errorCode;
        var oAuth2Error = new OAuth2Error(errorCode, request.getParameter(OAuth2ParameterNames.ERROR_DESCRIPTION), request.getParameter(OAuth2ParameterNames.ERROR_URI));
        System.out.println(oAuth2Error.getUri());
        System.out.println(oAuth2Error);
        if (StringUtils.hasText(errorCode)) {
            errorText += "<br/>" + request.getParameter(OAuth2ParameterNames.ERROR_DESCRIPTION) + "<br/>"
                         + request.getParameter(OAuth2ParameterNames.ERROR_URI);
        }
        return errorText;
    }

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public Object authorizationCodeGrant(@RegisteredOAuth2AuthorizedClient("livk-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
        return this.webClient
                .get()
                .uri(resourceBaseUri)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }

    @GetMapping(value = "/authorize", params = "grant_type=client_credentials")
    public Object clientCredentialsGrant() {
        return this.webClient
                .get()
                .uri(this.resourceBaseUri)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("livk-client-client-credentials"))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
    }
}
