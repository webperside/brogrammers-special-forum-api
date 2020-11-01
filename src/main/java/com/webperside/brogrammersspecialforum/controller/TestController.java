package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.dto.request.TokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.response.TokenResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"index", "/"})
public class TestController {

    @GetMapping
    public String index() {
        return "<h1><i>Hello from the other side</i></h1>";
    }

    @PostMapping("/token")
    public TokenResponseDto auth(@RequestBody TokenRequestDto tokenRequestDto) throws InterruptedException {
        TokenResponseDto result = new TokenResponseDto();
        result.setAccessToken(tokenRequestDto.getUsername() + tokenRequestDto.getPassword() + "ACCESS_TOKEN");
        result.setRefreshToken(tokenRequestDto.getUsername() + tokenRequestDto.getPassword() + "REFRESH_TOKEN&REMEMBER_ME=" + tokenRequestDto.isRememberMe());
//        Thread.sleep(5000);
        return result;
    }
}
