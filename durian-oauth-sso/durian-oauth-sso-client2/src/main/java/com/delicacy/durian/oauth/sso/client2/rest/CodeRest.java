package com.delicacy.durian.oauth.sso.client2.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "code")
public class CodeRest {

    static String url = "http://localhost:8000/oauth/token?grant_type=authorization_code&client_id=client_id_1&client_secret=123456&redirect_uri=http://localhost:8001/code&code=";

    @GetMapping
    public String get(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url +code, String.class);
        return forEntity.getBody();
    }


}