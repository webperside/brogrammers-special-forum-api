/* start create user table */
create table if not exists user
(
    user_id             int auto_increment primary key,
    username            varchar(50)                             not null,
    password            varchar(256)                            not null,
    full_name           varchar(100)                            not null,
    avatar              varchar(20)                             not null,
    role                tinyint                                 not null comment '0 - ADMIN; 1 - USER;',
    profile_visit_count int default 0                           null,
    created_at          timestamp default current_timestamp     not null,
    modified_at         timestamp                               null,
    constraint uix_username_user unique (username)
);
create index ix_created_at_user on user (created_at);
create index ix_modified_at_user on user (modified_at);
/* finish create user table */

/* start create user_authorization table */
create table if not exists user_authorization
(
    user_authorization_id       int auto_increment primary key,
    fk_user_id                  int                                     not null,
    access_token                varchar(50)                             null,
    access_token_expire_date    timestamp                               null,
    refresh_token               varchar(50)                             null,
    refresh_token_expire_date   timestamp                               null,
    created_at                  timestamp default current_timestamp     not null,
    modified_at                 timestamp                               null,
    constraint uix_access_token_user_authorization unique (access_token),
    constraint uix_refresh_token_user_authorization unique (refresh_token),
    constraint fk_user_id_user_authorization foreign key (fk_user_id) references user (user_id)
);
create index ix_created_at_user_authorization on user_authorization (created_at);
create index ix_modified_at_user_authorization on user_authorization (modified_at);
/* finish create user_authorization table */

/* start create follow table */
create table if not exists follow
(
    follow_id       int auto_increment primary key,
    from_user_id    int                                     not null,
    to_user_id      int                                     not null,
    created_at      timestamp default current_timestamp     not null,
    constraint fk_from_user_id_follow foreign key (from_user_id) references user(user_id),
    constraint fk_to_user_id_follow foreign key (to_user_id) references user(user_id),
);
create index ix_created_at_follow on follow (created_at);
/* finish create follow table */
