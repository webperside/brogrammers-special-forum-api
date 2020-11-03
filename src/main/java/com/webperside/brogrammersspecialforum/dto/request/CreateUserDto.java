package com.webperside.brogrammersspecialforum.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotEmpty(message = "fullName")
    @ApiModelProperty(position = 1)
    private String fullName;

    @NotEmpty(message = "username")
    @ApiModelProperty(position = 2)
    private String username;

    @NotEmpty(message = "password")
    @ApiModelProperty(position = 3)
    private String password;

}
