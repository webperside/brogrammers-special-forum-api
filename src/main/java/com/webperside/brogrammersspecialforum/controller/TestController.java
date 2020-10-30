package com.webperside.brogrammersspecialforum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"index", "/"})
public class TestController {

    @GetMapping
    public String index(){
        return "<h1><i>Hello from the other side</i></h1>";
    }
    
    @GetMapping("/auth")
    public boolean auth(@RequestParam(value = "isAuthenticated",defaultValue = "false") boolean isAuthenticated){
        return isAuthenticated;
    }
}
