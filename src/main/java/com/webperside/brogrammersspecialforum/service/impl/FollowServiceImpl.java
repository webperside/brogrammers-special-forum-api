package com.webperside.brogrammersspecialforum.service.impl;

import com.webperside.brogrammersspecialforum.models.Follow;
import com.webperside.brogrammersspecialforum.models.User;
import com.webperside.brogrammersspecialforum.repository.FollowRepository;
import com.webperside.brogrammersspecialforum.service.FollowService;
import com.webperside.brogrammersspecialforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static com.webperside.brogrammersspecialforum.utils.UserUtils.CURRENT_USER;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final UserService userService;
    private final FollowRepository followRepository;

    @Override
    public void followUser(Integer userId) {
        User fromUser = CURRENT_USER();
        User toUser = userService.getById(userId);
        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .createdAt(Instant.now())
                .build();

        followRepository.save(follow);
    }

    @Override
    public void unfollowUser(Integer userId) {
        User fromUser = CURRENT_USER();
        User toUser = userService.getById(userId);
        Optional<Follow> follow = followRepository.findByFromUserAndToUser(fromUser, toUser);

        follow.ifPresent(followRepository::delete);
    }
}
