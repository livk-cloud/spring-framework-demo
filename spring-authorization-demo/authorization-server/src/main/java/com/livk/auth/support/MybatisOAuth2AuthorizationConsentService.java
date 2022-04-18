package com.livk.auth.support;

import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;

/**
 * <p>
 * MybatisOAuth2AuthorizationConsentService
 * </p>
 *
 * @author livk
 * @date 2022/4/1
 */
public class MybatisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    @Override
    public void save(OAuth2AuthorizationConsent oAuth2AuthorizationConsent) {

    }

    @Override
    public void remove(OAuth2AuthorizationConsent oAuth2AuthorizationConsent) {

    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return null;
    }
}
