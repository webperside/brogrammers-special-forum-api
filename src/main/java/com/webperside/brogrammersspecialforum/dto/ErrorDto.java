package com.webperside.brogrammersspecialforum.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDto {

    private Integer status;
    private Integer code;
    private String message;
    private Date timestamp;
}
