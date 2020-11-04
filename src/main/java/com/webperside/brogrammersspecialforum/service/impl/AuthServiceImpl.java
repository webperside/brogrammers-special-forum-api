package com.webperside.brogrammersspecialforum.service.impl;

import com.webperside.brogrammersspecialforum.dto.request.RefreshTokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.request.TokenRequestDto;
import com.webperside.brogrammersspecialforum.dto.response.TokenResponseDto;
import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.exception.RestException;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.models.UserAuthorization;
import com.webperside.brogrammersspecialforum.repository.UserAuthorizationRepository;
import com.webperside.brogrammersspecialforum.security.JwtTokenUtil;
import com.webperside.brogrammersspecialforum.service.AuthService;
import com.webperside.brogrammersspecialforum.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.ZoneId;
import java.util.Optional;

import static com.webperside.brogrammersspecialforum.utils.UserUtils.CURRENT_USER;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserAuthorizationRepository userAuthorizationRepository;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenResponseDto getToken(TokenRequestDto tokenRequestDto) {
        authenticate(tokenRequestDto);

        User user = userService.getByUsername(tokenRequestDto.getUsername());

        String accessToken = jwtTokenUtil.createAccessToken(user);
        String refreshToken = jwtTokenUtil.createRefreshToken(user, tokenRequestDto.isRememberMe());

        TokenResponseDto response = TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        saveUserAuthorization(response, user);

        return response;
    }

    @Override
    public TokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        userAuthorizationRepository.findByRefreshToken(
                DigestUtils.md5DigestAsHex(refreshTokenRequestDto.getRefreshToken().getBytes()))
                .orElseThrow(() -> new RestException(ErrorEnum.REFRESH_TOKEN_EXPIRED_EXCEPTION));

        if (jwtTokenUtil.validateRefreshToken(refreshTokenRequestDto.getRefreshToken())) {
            String username = jwtTokenUtil.getUsernameFromToken(refreshTokenRequestDto.getRefreshToken());

            User user = userService.getByUsername(username);

            String accessToken = jwtTokenUtil.createAccessToken(user);
            String refreshToken = jwtTokenUtil.createRefreshToken(user, refreshTokenRequestDto.isRememberMe());

            TokenResponseDto response = TokenResponseDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            saveUserAuthorization(response, user);

            return response;
        }
        return null;
    }

    @Override
    public void logout() {
        User user = CURRENT_USER();
        UserAuthorization userAuthorization = user.getUserAuthorizations().get(0);
        userAuthorization.setAccessToken(null);
        userAuthorization.setRefreshToken(null);
        userAuthorization.setAccessTokenExpireDate(null);
        userAuthorization.setRefreshTokenExpireDate(null);
        userAuthorizationRepository.save(userAuthorization);
    }

    // private util methods

    private void saveUserAuthorization(TokenResponseDto tokens, User user) {
        Optional<UserAuthorization> userAuthorizationOpt = userAuthorizationRepository.findByUserId(user.getId());

        UserAuthorization userAuthorization;
        if (userAuthorizationOpt.isPresent()) {
            userAuthorization = userAuthorizationOpt.get();
        } else {
            userAuthorization = new UserAuthorization();
            userAuthorization.setUser(user);
        }

        userAuthorization.setAccessToken(DigestUtils.md5DigestAsHex(
                tokens.getAccessToken().getBytes()));
        userAuthorization.setRefreshToken(DigestUtils.md5DigestAsHex(
                tokens.getRefreshToken().getBytes()));
        userAuthorization.setAccessTokenExpireDate(jwtTokenUtil
                .getExpirationDateFromToken(tokens.getAccessToken()).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime());
        userAuthorization.setRefreshTokenExpireDate(jwtTokenUtil
                .getExpirationDateFromToken(tokens.getRefreshToken()).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime());

        userAuthorizationRepository.save(userAuthorization);
    }

    private void authenticate(TokenRequestDto tokenRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    tokenRequestDto.getUsername(),
                    tokenRequestDto.getPassword()
            ));
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage());
        }
    }
}
