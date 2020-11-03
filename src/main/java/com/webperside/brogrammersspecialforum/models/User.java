package com.webperside.brogrammersspecialforum.models;

import com.webperside.brogrammersspecialforum.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "full_name", nullable = false , length = 100)
    private String fullName;

    @Column(name = "avatar", nullable = false, length = 20)
    private String avatar;

    @Column(name = "role", nullable = false, columnDefinition = "tinyint(4)")
    private Role role;

    @Column(name = "profile_visit_count", nullable = false)
    private Integer profileVisitCount;

    @OneToMany(mappedBy = "user",fetch = LAZY)
    private List<UserAuthorization> userAuthorizations;

    @OneToMany(mappedBy = "fromUser", fetch = LAZY)
    private List<Follow> following;

    @OneToMany(mappedBy = "toUser", fetch = LAZY)
    private List<Follow> followers;

    @OneToMany(mappedBy = "user", fetch = LAZY)
    private List<Title> titles;

    @OneToMany(mappedBy = "createdBy", fetch = LAZY)
    private List<Category> createdCategories;

}
