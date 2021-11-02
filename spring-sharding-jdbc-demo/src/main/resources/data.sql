create table sys_log
(
    `id`   bigint     not null auto_increment primary key,
    `ip`   varchar(16) default null comment 'ip',
    `os`   varchar(20) default null comment '操作系统',
    `data` json        default null comment '数据',
    `date` varchar(8) not null comment '分表键位'
)
