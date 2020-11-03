package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.dto.request.RefreshTokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.request.TokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.response.TokenResponseDto;
import com.webperside.brogrammersspecialforum.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public TokenResponseDto getToken(@RequestBody @Validated TokenRequestDto tokenRequestDto){
        return authService.getToken(tokenRequestDto);
    }

    @PostMapping("/refresh-token")
    public TokenResponseDto refreshToken(@RequestBody @Validated RefreshTokenRequestDto refreshTokenRequestDto){
        return authService.refreshToken(refreshTokenRequestDto);
    }

    @GetMapping("/logout")
    public void logout(){
        authService.logout();
    }
}
