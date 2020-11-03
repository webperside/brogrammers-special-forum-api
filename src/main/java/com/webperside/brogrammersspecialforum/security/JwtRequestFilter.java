package com.webperside.brogrammersspecialforum.security;

import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.models.UserAuthorization;
import com.webperside.brogrammersspecialforum.repository.UserAuthorizationRepository;
import com.webperside.brogrammersspecialforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final UserAuthorizationRepository userAuthorizationRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String token = httpServletRequest.getHeader("Authorization");

        if(token != null && jwtTokenUtil.validateAccessToken(token)){

            Optional<User> optUser = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
            if(optUser.isPresent()){
                if (checkAccessTokenIsExist(token, optUser.get())) {
                    Authentication auth = jwtTokenUtil.getAuthentication(token);

                    if(auth != null){
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean checkAccessTokenIsExist(String token, User user) {
        UserAuthorization opt = userAuthorizationRepository.findByUserId(user.getId()).orElse(null);
        if(opt == null){
            System.out.println("throw token not found exception"); //todo custom exception
            return false;
        } else {
            return opt.getAccessToken().equals(DigestUtils.md5DigestAsHex(token.getBytes()));
        }
//        userAuthorization -> userAuthorization.getAccessToken().equals(DigestUtils.md5DigestAsHex(token.getBytes()))
    }
}
