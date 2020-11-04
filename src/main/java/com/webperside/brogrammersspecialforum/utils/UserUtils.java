package com.webperside.brogrammersspecialforum.utils;

import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.exception.RestException;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.repository.UserRepository;
import com.webperside.brogrammersspecialforum.security.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    private static UserRepository userRepository;

    public UserUtils(UserRepository userRepository) {
        UserUtils.userRepository = userRepository;
    }

    public static User CURRENT_USER() {
        String username = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();

        return getByUsername(username);
    }

    // private util methods

    private static User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION,username));
    }


}
