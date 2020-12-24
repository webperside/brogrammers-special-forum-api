package com.webperside.brogrammersspecialforum.service.impl;

import com.webperside.brogrammersspecialforum.dto.request.CreateUserDto;
import com.webperside.brogrammersspecialforum.dto.response.UserShortInfoDto;
import com.webperside.brogrammersspecialforum.enums.ErrorEnum;
import com.webperside.brogrammersspecialforum.enums.Role;
import com.webperside.brogrammersspecialforum.exception.RestException;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.repository.UserRepository;
import com.webperside.brogrammersspecialforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.webperside.brogrammersspecialforum.utils.UserUtils.CURRENT_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Integer createUser(CreateUserDto createUserDto) {

        if(userRepository.existsUserByUsername(createUserDto.getUsername())){
            throw new RestException(ErrorEnum.USERNAME_ALREADY_EXIST_EXCEPTION, createUserDto.getUsername());
        }

        User user = User.builder()
                .fullName(createUserDto.getFullName())
                .username(createUserDto.getUsername())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .role(Role.USER)
                .avatar(generateAvatar())
                .profileVisitCount(0)
                .build();

        userRepository.save(user);
        return user.getId();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RestException(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION,username));
    }

    @Override
    public UserShortInfoDto getUserShortInfo() {
        return UserShortInfoDto.fromEntity(CURRENT_USER());
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RestException(ErrorEnum.USER_NOT_FOUND_EXCEPTION)
        );
    }

    // private util methods

    private String generateAvatar() {
        String[] colors = {
                "#FFB900",
                "#D83B01",
                "#B50E0E",
                "#E81123",
                "#B4009E",
                "#5C2D91",
                "#0078D7",
                "#00B4FF",
                "#008272",
                "#107C10"
        };

        int randomColorIndex = new Random().nextInt(colors.length); // -1
        return colors[randomColorIndex];
    }
}
