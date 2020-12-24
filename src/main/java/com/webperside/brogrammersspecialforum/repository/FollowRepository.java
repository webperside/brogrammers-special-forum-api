package com.webperside.brogrammersspecialforum.repository;

import com.webperside.brogrammersspecialforum.models.Follow;
import com.webperside.brogrammersspecialforum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByFromUserAndToUser(User fromUser, User toUser);

}
