package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.dto.request.CreateUserDto;
import com.webperside.brogrammersspecialforum.dto.response.UserShortInfoDto;
import com.webperside.brogrammersspecialforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public Integer signUp(@Validated @RequestBody CreateUserDto createUserDto){
        return userService.createUser(createUserDto);
    }

    @GetMapping("/user-info")
    public UserShortInfoDto getUserShortInfo(){
        return userService.getUserShortInfo();
    }

}
