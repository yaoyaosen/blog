create table user
(
    id          int(10) auto_increment comment '主键'
        primary key,
    user_name   varchar(50) not null comment '用户名',
    gender      int(1)      null comment '性别（1男 2女 0未知）',
    phone       varchar(20) null comment '电话',
    weixin      varchar(20) null comment '微信号',
    qq          varchar(20) null comment 'QQ',
    email       varchar(50) null comment '邮箱',
    create_date datetime    null comment '创建日期',
    update_date datetime    null comment '修改日期',
    status      int(1)      null comment '用户状态（1可用 2不可用 0删除）',
    power       int(1)      null comment '用户权限（1普通 2admin）'
);

