package com.webperside.brogrammersspecialforum.exception;

import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class RestException extends RuntimeException{

    private HttpStatus httpStatus;
    private Integer code;
    private String message;

    public RestException(ErrorEnum errorEnum) {
        this.httpStatus = errorEnum.getHttpStatus();
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

    public RestException(ErrorEnum errorEnum, String extraData) {
        String extraMessage = String.format(errorEnum.getMessage(), extraData);
        this.httpStatus = errorEnum.getHttpStatus();
        this.code = errorEnum.getCode();
        this.message = extraMessage;
    }
}
