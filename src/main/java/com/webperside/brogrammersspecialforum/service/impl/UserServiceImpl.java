package com.webperside.brogrammersspecialforum.service.impl;

import com.webperside.brogrammersspecialforum.dto.request.CreateUserDto;
import com.webperside.brogrammersspecialforum.enums.Role;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.repository.UserRepository;
import com.webperside.brogrammersspecialforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Integer createUser(CreateUserDto createUserDto) {
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
        return userRepository.findByUsername(username) // todo custom exception
                .orElseThrow(() -> new UsernameNotFoundException("username % not found"));
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
