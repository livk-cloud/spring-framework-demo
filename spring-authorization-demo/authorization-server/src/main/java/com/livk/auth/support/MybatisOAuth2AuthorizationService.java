package com.livk.auth.support;

import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;

/**
 * <p>
 * MybatisOAuth2AuthorizationService
 * </p>
 *
 * @author livk
 * @date 2022/4/1
 */
public class MybatisOAuth2AuthorizationService implements OAuth2AuthorizationService {
    @Override
    public void save(OAuth2Authorization oAuth2Authorization) {

    }

    @Override
    public void remove(OAuth2Authorization oAuth2Authorization) {
        //mapper.remove(oAuth2Authorization.getId())
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return null;
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType oAuth2TokenType) {
        return null;
    }
}
