package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
public class HelloController {

    @Resource
    private JwtTokenStore tokenStore;

    @RequestMapping("/hello")
    public String hello(Principal principal){
        return "hello "+principal.getName();
    }

    @RequestMapping("/user")
    public Principal getUser(String access_token){
        Authentication authentication=tokenStore.readAuthentication(access_token);
        Authentication userAuthentication=tokenStore.readAuthentication(access_token).getUserAuthentication();

        System.out.println("authentication："+authentication);
        System.out.println("userAuthentication："+userAuthentication);

        return userAuthentication;
    }
}