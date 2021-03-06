package com.delicacy.durian.oauth.config.oauth;


import com.delicacy.durian.oauth.handler.DefaultOAuth2AccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedHandler(new DefaultOAuth2AccessDeniedHandler());
        http.requestMatchers().antMatchers("/user/**")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();


    }

}