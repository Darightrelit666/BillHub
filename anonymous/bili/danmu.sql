create table danmu
(
    id            bigint                                not null
        primary key,
    video_id      bigint                                not null comment '弹幕所属视频ID，关联 video.id',
    user_id       bigint                                null comment '发送者用户ID，关联 user.id（可为空，游客弹幕）',
    content       varchar(255)                          not null comment '弹幕内容',
    position_time double                                not null comment '弹幕出现时间（秒）',
    color         varchar(20) default '#FFFFFF'         not null comment '弹幕字体颜色',
    font_size     int         default 25                not null comment '弹幕字体大小',
    type          varchar(20) default 'scroll'          not null comment '弹幕类型：scroll/top/bottom',
    created_at    datetime    default CURRENT_TIMESTAMP not null comment '发送时间',
    constraint fk_danmu_user
        foreign key (user_id) references user (id)
            on update cascade on delete set null,
    constraint fk_danmu_video
        foreign key (video_id) references video (id)
            on update cascade on delete cascade
)
    collate = utf8mb4_unicode_ci;

create index idx_danmu_user
    on danmu (user_id);

create index idx_danmu_video
    on danmu (video_id);

