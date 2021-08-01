package com.huaxin.api.controller;

import com.huaxin.api.security.util.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cxks
 */
@Controller
@RequestMapping("/templates")
public class TestController {

    private final JwtTokenUtils jwtTokenUtils;

    public TestController(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    static final String LAST = ".html";

    @GetMapping("/{template}")
    public String template(@PathVariable String template){
        if(template.indexOf(LAST) != -1){
            return template;
        }
        return null;
    }
}
