package com.webperside.brogrammersspecialforum.controller;

import com.webperside.brogrammersspecialforum.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follow/{userId}")
    public void followUser(@PathVariable("userId") Integer userId){
        followService.followUser(userId);
    }

    @GetMapping("/unfollow/{userId}")
    public void unfollowUser(@PathVariable("userId") Integer userId){
        followService.unfollowUser(userId);
    }
}
