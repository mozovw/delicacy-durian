package com.delicacy.durian.oauth.config.oauth;

import com.delicacy.durian.oauth.constants.SecurityConstants;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

public class DefaultClientDetailsService extends JdbcClientDetailsService {

    public DefaultClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @SneakyThrows
    @Cacheable(value = SecurityConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        return clientDetails;
    }
}
