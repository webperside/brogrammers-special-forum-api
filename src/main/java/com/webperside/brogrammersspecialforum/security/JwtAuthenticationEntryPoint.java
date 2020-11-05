package com.webperside.brogrammersspecialforum.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webperside.brogrammersspecialforum.dto.ErrorDto;
import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException{
        ErrorDto errorDto = ErrorDto.fromRestException(new RestException(ErrorEnum.ACCESS_TOKEN_EXPIRED_EXCEPTION));
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
