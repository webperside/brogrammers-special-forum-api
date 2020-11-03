package com.webperside.brogrammersspecialforum.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_authorization")
public class UserAuthorization extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_authorization_id")
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Column(name = "access_token", unique = true, length = 50)
    private String accessToken;

    @Column(name = "access_token_expire_date")
    private LocalDateTime accessTokenExpireDate;

    @Column(name = "refresh_token", unique = true, length = 50)
    private String refreshToken;

    @Column(name = "refresh_token_expire_date")
    private LocalDateTime refreshTokenExpireDate;

}
