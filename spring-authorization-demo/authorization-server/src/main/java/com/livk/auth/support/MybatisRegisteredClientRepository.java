package com.livk.auth.support;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>
 * MybatisRegisteredClientRepository
 * </p>
 *
 * @author livk
 * @date 2022/4/1
 */
public class MybatisRegisteredClientRepository implements RegisteredClientRepository {
    @Override
    public void save(RegisteredClient registeredClient) {
        RegisteredClient clientBase = this.findByClientId(registeredClient.getClientId());
        //mapper.saveOrUpdate(id,data)
    }

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return null;
    }
}
