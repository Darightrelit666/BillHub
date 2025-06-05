create table user
(
    id            bigint  comment '主键，用uid'
        primary key,
    username      varchar(50)                           not null comment '用户名，唯一',
    password_hash varchar(100)                          not null comment 'BCrypt 加密后的密码',
    email         varchar(100)                          null comment '邮箱，可选',
    role          varchar(20) default 'USER'            not null comment '用户角色，默认为 USER',
    avatar_url    varchar(255)                          null comment '头像 URL，可选',
    create_time   datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_user_username
        unique (username)
)
    comment '用户表' charset = utf8mb4;

