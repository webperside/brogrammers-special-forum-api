package com.webperside.brogrammersspecialforum.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
}
