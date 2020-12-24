package com.webperside.brogrammersspecialforum.service;

public interface FollowService {

    void followUser(Integer userId);

    void unfollowUser(Integer userId);
}
