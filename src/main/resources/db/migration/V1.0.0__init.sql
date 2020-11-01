/* start create user table */
create table if not exists user
(
    user_id             int auto_increment primary key,
    username            varchar(50)                             not null,
    password            varchar(256)                            not null,
    full_name           varchar(100)                            not null,
    avatar              varchar(20)                             not null,
    role                tinyint                                 not null comment '0 - ADMIN; 1 - USER;',
    profile_visit_count int default 0                           not null,
    created_at          timestamp default current_timestamp     not null,
    modified_at         timestamp                               null,
    constraint uix_username_user unique (username)
);
create index ix_username_user on user(username);
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
create index ix_fk_user_id_user_authorization on user_authorization (fk_user_id);
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
    constraint fk_to_user_id_follow foreign key (to_user_id) references user(user_id)
);
create index ix_from_user_id_follow on follow (from_user_id);
create index ix_to_user_id_follow on follow (to_user_id);
create index ix_created_at_follow on follow (created_at);
/* finish create follow table */

/* start create title table */
create table if not exists title
(
    title_id        int auto_increment primary key,
    fk_user_id      int                                     not null,
    name            varchar(100)                            not null,
    url_name        varchar(100)                            not null,
    seen_count      int default 0                           not null,
    is_trend        tinyint                                 not null,
    created_at      timestamp default current_timestamp     not null,
    modified_at     timestamp                               null,
    constraint uix_name_title unique (name),
    constraint uix_url_name_title unique (url_name),
    constraint fk_user_id_title foreign key (fk_user_id) references user(user_id)
);
create index ix_fk_user_id_title on title(fk_user_id);
create index ix_url_name_title on title(url_name);
create index ix_is_trend_title on title(is_trend);
create index ix_created_at_title on title(created_at);
create index ix_modified_at_title on title(modified_at);
/* finish create title table */

/* start create reply table */
create table if not exists reply
(
    reply_id        int auto_increment primary key,
    fk_title_id     int                                     not null,
    fk_user_id      int                                     not null,
    content         MEDIUMBLOB                              not null,
    created_at      timestamp default current_timestamp     not null,
    modified_at     timestamp                               null,
    constraint fk_title_id_reply foreign key (fk_title_id) references title(title_id),
    constraint fk_user_id_reply foreign key (fk_user_id) references user(user_id)
);
create index ix_fk_title_id_reply on reply(fk_title_id);
create index ix_fk_user_id_reply on reply(fk_user_id);
create index ix_created_at_reply on reply(created_at);
create index ix_modified_at_reply on reply(modified_at);
/* finish create reply table */

/* start create table category */
create table if not exists category
(
    category_id     int auto_increment primary key,
    name            varchar(100)                            not null,
    url_name        varchar(100)                            not null,
    created_at      timestamp default current_timestamp     not null,
    created_by      int                                     not null,
    modified_at     timestamp                               null,
    constraint fk_created_by_category foreign key (created_by) references user(user_id)
);
create index ix_created_by_category on category(created_by);
create index ix_created_at_category on category(created_at);
create index ix_modified_at_category on category(modified_at);
/* finish create table category */

/* start create table title_category */
create table if not exists title_category
(
    title_category_id   int auto_increment primary key,
    fk_title_id         int                                     not null,
    fk_category_id      int                                     not null,
    created_at          timestamp default current_timestamp     not null,
    modified_at         timestamp                               null,
    constraint fk_title_id_title_category foreign key (fk_title_id) references title(title_id),
    constraint fk_category_id_title_category foreign key (fk_category_id) references category(category_id)
);
create index ix_fk_title_id_title_category on title_category(fk_title_id);
create index ix_fk_category_id_title_category on title_category(fk_category_id);
create index ix_created_at_title_category on title_category(created_at);
create index ix_modified_at_title_category on title_category(modified_at);
/* finish create table title_category */

/* start create table subscribe_title */
create table if not exists subscribe_title
(
    subscribe_id        int auto_increment primary key,
    fk_title_id         int                                     not null,
    fk_user_id          int                                     not null,
    created_at          timestamp default current_timestamp     not null,
    constraint fk_title_id_subscribe_title foreign key (fk_title_id) references title(title_id),
    constraint fk_user_id_subscribe_title foreign key (fk_user_id) references user(user_id)
);
create index ix_fk_title_id_subscribe_title on subscribe_title(fk_title_id);
create index ix_fk_user_id_subscribe_title on subscribe_title(fk_user_id);
create index ix_created_at_subscribe_title on subscribe_title(created_at);
/* finish create table subscribe_title */

/* start create table notification */
create table if not exists notification
(
    notification_id     int auto_increment primary key ,
    status              tinyint                                 not null comment '0 - NOT_READ, 1 - READ',
    notification_type   tinyint                                 not null comment '0 - REPLY , 1 - FOLLOW',
    created_at          timestamp default current_timestamp     not null,
    fk_title_id         int                                     null,
    from_user_id        int                                     not null,
    to_user_id          int                                     not null,
    constraint fk_title_id_notification foreign key (fk_title_id) references title(title_id),
    constraint fk_from_user_id_notification foreign key (from_user_id) references user(user_id),
    constraint fk_to_user_id_notification foreign key (to_user_id) references user(user_id)
);
create index ix_fk_title_id_notification on notification(fk_title_id);
create index ix_from_user_id_notification on notification(from_user_id);
create index ix_to_user_id_notification on notification(to_user_id);
create index ix_created_at_notification on notification(created_at);
/* finish create table notification */