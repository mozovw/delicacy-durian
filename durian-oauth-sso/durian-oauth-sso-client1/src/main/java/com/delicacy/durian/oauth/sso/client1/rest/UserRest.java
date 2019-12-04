package com.delicacy.durian.oauth.sso.client1.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "user")
public class UserRest {

    @GetMapping(value = "get")
    public Principal get(Principal principal) {
        System.out.println("调用me接口获取用户信息：" + principal);
        return principal;
    }
}