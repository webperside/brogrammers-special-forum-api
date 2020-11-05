package com.webperside.brogrammersspecialforum.repository;

import com.webperside.brogrammersspecialforum.models.UserAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization,Integer> {

    Optional<UserAuthorization> findByUserId(Integer userId);

    Optional<UserAuthorization> findByRefreshToken(String token);

    List<UserAuthorization> findAllByUserId(Integer userId);
}
