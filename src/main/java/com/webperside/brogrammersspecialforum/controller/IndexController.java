package com.webperside.brogrammersspecialforum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/","/index"})
public class IndexController {

    @GetMapping
    public String index(){
        return "<a href=\"/api/swagger-ui.html\">API Documentation</a>";
    }
}
