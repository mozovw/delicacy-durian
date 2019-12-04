package com.delicacy.durian.oauth.sso.server.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "user")
public class UserRest {

    @GetMapping
    public Principal get(Principal principal) {
        log.info("调用me接口获取用户信息：" + principal);
        return principal;
    }
}
