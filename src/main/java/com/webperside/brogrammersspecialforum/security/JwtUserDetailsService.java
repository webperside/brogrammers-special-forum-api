package com.webperside.brogrammersspecialforum.security;

import com.webperside.brogrammersspecialforum.enums.Role;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("username % not found")
        ); // todo custom exception

        return JwtUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(mapRoles(Collections.singletonList(user.getRole())))
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRoles(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
