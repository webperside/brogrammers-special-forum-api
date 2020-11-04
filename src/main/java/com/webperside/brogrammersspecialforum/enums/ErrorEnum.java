package com.webperside.brogrammersspecialforum.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    DEFAULT_EXCEPTION(100, "DEFAULT_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    ENUM_NOT_FOUND_EXCEPTION(101, "ENUM_NOT_FOUND_EXCEPTION", HttpStatus.NOT_FOUND),
    ACCESS_TOKEN_EXPIRED_EXCEPTION(102,"ACCESS_TOKEN_EXPIRED_EXCEPTION", HttpStatus.FORBIDDEN),
    REFRESH_TOKEN_EXPIRED_EXCEPTION(103, "REFRESH_TOKEN_EXPIRED_EXCEPTION", HttpStatus.FORBIDDEN),
    USERNAME_NOT_FOUND_EXCEPTION(104, "USERNAME_NOT_FOUND_EXCEPTION (target : %s)", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_EXCEPTION(105,"USER_NOT_FOUND_EXCEPTION",HttpStatus.NOT_FOUND);

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorEnum(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
