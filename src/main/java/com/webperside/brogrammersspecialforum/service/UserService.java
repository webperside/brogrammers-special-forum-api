package com.webperside.brogrammersspecialforum.service;

import com.webperside.brogrammersspecialforum.dto.request.CreateUserDto;
import com.webperside.brogrammersspecialforum.dto.response.UserShortInfoDto;
import com.webperside.brogrammersspecialforum.models.User;

public interface UserService {

    Integer createUser(CreateUserDto createUserDto);

    User getByUsername(String username);

    UserShortInfoDto getUserShortInfo();
}
