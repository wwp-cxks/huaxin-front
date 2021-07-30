package com.huaxin.api.controller;

import com.huaxin.api.security.util.JwtTokenUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JwtTokenUtils jwtTokenUtils;

    public TestController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @GetMapping(value = "/login")
    public String login(String user,String password){
        Map map = new HashMap();
        map.put("user","fdsa");
        map.put("password",1234);
        String token = jwtTokenUtils.createToken(map);
        System.out.println("token   " + token);
        System.out.println("getClaimsFromToken   " + jwtTokenUtils.getClaimsFromToken(token));
        System.out.println("getAuthentication   " + jwtTokenUtils.getAuthentication(token));
        System.out.println("validateToken   " + jwtTokenUtils.validateToken(token));
        System.out.println("getExpirationDateFromToken   " + jwtTokenUtils.getExpirationDateFromToken(token));
        return jwtTokenUtils.createToken(map);
    }

}
