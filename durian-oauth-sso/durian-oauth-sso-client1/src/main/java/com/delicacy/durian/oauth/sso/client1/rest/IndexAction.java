package com.delicacy.durian.oauth.sso.client1.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexAction {

    @GetMapping(value = "/index")
    public String index() {
        System.out.println("进入ClientA首页");
        return "index.html";
    }

    @GetMapping(value = "securedPage")
    public String home() {
        System.out.println("进入ClientA securedPage");
        return "securedPage.html";
    }
}