package com.webperside.brogrammersspecialforum.security;

import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.exception.RestException;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.models.UserAuthorization;
import com.webperside.brogrammersspecialforum.repository.UserAuthorizationRepository;
import com.webperside.brogrammersspecialforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final UserAuthorizationRepository userAuthorizationRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String token = httpServletRequest.getHeader("Authorization");

        try {
            if (token != null && jwtTokenUtil.validateAccessToken(token)) {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RestException(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION,username));
                if (checkAccessTokenIsExist(token, user)) {
                    Authentication auth = jwtTokenUtil.getAuthentication(token);

                    if (auth != null) {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                } else {
                    throw new RestException(ErrorEnum.ACCESS_TOKEN_EXPIRED_EXCEPTION);
                }
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public boolean checkAccessTokenIsExist(String token, User user) {
        return userAuthorizationRepository.findAllByUserId(user.getId()).stream()
                .anyMatch(ua ->
                        ua.getAccessToken().equals(DigestUtils.md5DigestAsHex(token.getBytes()))
                );
    }
}
