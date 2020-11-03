package com.webperside.brogrammersspecialforum.service;

import com.webperside.brogrammersspecialforum.dto.request.RefreshTokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.request.TokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.response.TokenResponseDto;

public interface AuthService {

    TokenResponseDto getToken(TokenRequestDto tokenRequestDto);

    TokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    void logout();
}
