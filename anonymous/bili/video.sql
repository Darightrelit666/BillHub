create table video
(
    id             bigint 
        primary key,
    uploader_id    bigint                                not null comment '上传者用户ID，关联 user.id',
    title          varchar(255)                          not null comment '视频标题',
    description    text                                  null comment '视频描述',
    storage_path   varchar(500)                          not null comment '视频存储路径或URL（转码后主文件）',
    thumbnail_path varchar(500)                          null comment '缩略图路径或URL',
    duration       int                                   null comment '视频时长（秒）',
    size           bigint                                null comment '视频大小（字节）',
    status         varchar(50) default 'PENDING'         not null comment '状态：PENDING、READY、FAILED等',
    view_count     bigint      default 0                 not null comment '播放量',
    created_at     datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_video_uploader
        foreign key (uploader_id) references user (id)
            on update cascade on delete cascade
)
    collate = utf8mb4_unicode_ci;

create index idx_video_uploader
    on video (uploader_id);

