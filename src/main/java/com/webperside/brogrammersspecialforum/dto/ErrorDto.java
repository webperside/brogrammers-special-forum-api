package com.webperside.brogrammersspecialforum.dto;

import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.exception.RestException;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ErrorDto {

    private static final Map<Integer, String> messages;

    static {
        messages = new HashMap<>();
        messages.put(ErrorEnum.DEFAULT_EXCEPTION.getCode(),"Daxili xəta baş verdi");
        messages.put(ErrorEnum.ENUM_NOT_FOUND_EXCEPTION.getCode(),"Seçilən məlumat mövcud deyil");
        messages.put(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION.getCode(),"%s adlı istifadəçi mövcud deyil");
        messages.put(ErrorEnum.USER_NOT_FOUND_EXCEPTION.getCode(),"%s adlı istifadəçi mövcud deyil");
        messages.put(ErrorEnum.PASSWORD_NOT_EQUALS_EXCEPTION.getCode(), "Şifrə düzgün daxil edilməyib");
        messages.put(ErrorEnum.USERNAME_ALREADY_EXIST_EXCEPTION.getCode(), "%s adlı istifadəçi artıq mövcuddur");
    }


    private Integer status;
    private Integer code;
    private String message;
    private String developerMessage;
    private Date timestamp;

    public static ErrorDto fromRestException(RestException ex) {
        boolean userMessageExist = messages.containsKey(ex.getCode());
        return ErrorDto
                .builder()
                .status(ex.getHttpStatus().value())
                .code(ex.getCode())
                .message(userMessageExist ? String.format(messages.get(ex.getCode()),ex.getTarget()) : null)
                .developerMessage(ex.getMessage())
                .timestamp(new Date())
                .build();
    }
}
