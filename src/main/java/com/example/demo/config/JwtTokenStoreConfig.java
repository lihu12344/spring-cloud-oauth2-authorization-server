package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

@Configuration
public class JwtTokenStoreConfig {

    @Resource
    private CustomUserAuthenticationConverter customUserAuthenticationConverter;

    @Bean
    public JwtAccessTokenConverter initJwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter=new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("sign123456");

        DefaultAccessTokenConverter defaultAccessTokenConverter=new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(customUserAuthenticationConverter);
        jwtAccessTokenConverter.setAccessTokenConverter(defaultAccessTokenConverter);
        //默认只输出认证用户名，修改后将authentication整体输出

        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore initJwtTokenStore(){
        return new JwtTokenStore(initJwtAccessTokenConverter());
    }
}
