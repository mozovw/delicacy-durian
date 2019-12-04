package com.delicacy.durian.oauth.config.oauth;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    @SneakyThrows
    public void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService());
    }


    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.formLogin().permitAll();
        http.logout().permitAll().deleteCookies("JSESSIONID");
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/**").authenticated();
    }


    @Override
    @Bean
    @SneakyThrows
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }

}