package com.webperside.brogrammersspecialforum.dto.response;

import com.webperside.brogrammersspecialforum.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserShortInfoDto {

    private String fullName;
    private String username;
    private String avatar;

    public static UserShortInfoDto fromEntity(User user){
        return UserShortInfoDto.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .build();
    }
}
