package com.webperside.brogrammersspecialforum.exception;

import com.webperside.brogrammersspecialforum.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorDto> handleRestException(RestException ex) {
        ErrorDto errorDto = ErrorDto.fromRestException(ex);
        return new ResponseEntity<>(errorDto, ex.getHttpStatus());
    }

}
