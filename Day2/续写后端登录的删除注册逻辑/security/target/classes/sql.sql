# create table user
# (
#     id          bigint auto_increment primary key      not null comment '主键',
#     user_name   varchar(64)  default ''                not null comment '用户名',
#     nick_name   varchar(64)  default ''                null comment '昵称',
#     password    varchar(64)  default ''                not null comment '密码',
#     status      char         default '0'               null comment '账号状态（0正常 1停用）',
#     email       varchar(64)  default ''                null comment '邮箱',
#     phonenumber varchar(32)  default ''                null comment '手机号',
#     sex         char         default ''                null comment '用户性别（0男，1女，2未知 ）',
#     avatar      varchar(128) default ''                null comment '头像',
#     user_type   char         default '1'               not null comment '用户类型（0管理员，1普通用户）',
#     create_time datetime     default CURRENT_TIMESTAMP null comment '创建时间',
#     del_flag    tinyint(1)   default 0                 null comment '删除标志（0代表未删除，1代表已删除）'
# ) auto_increment = 2
#     comment '用户表';

# CREATE TABLE `user`
# (
#     `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
#     `user_name`   VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
#     `nick_name`   VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
#     `password`    VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
#     `status`      CHAR(1)              DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
#     `email`       VARCHAR(64)          DEFAULT NULL COMMENT '邮箱',
#     `phonenumber` VARCHAR(32)          DEFAULT NULL COMMENT '手机号',
#     `sex`         CHAR(1)              DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
#     `avatar`      VARCHAR(128)         DEFAULT NULL COMMENT '头像',
#     `user_type`   CHAR(1)     NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
#     `create_time` DATETIME             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `del_flag`    INT(11)              DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
#     PRIMARY KEY (`id`)
# ) ENGINE = INNODB
#   AUTO_INCREMENT = 1
#   DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';


CREATE TABLE `user`
(
    `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`   VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `nick_name`   VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
    `password`    VARCHAR(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
    `status`      CHAR(1)              DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
    `email`       VARCHAR(64)          DEFAULT NULL COMMENT '邮箱',
    `phonenumber` VARCHAR(32)          DEFAULT NULL COMMENT '手机号',
    `sex`         CHAR(1)              DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
    `avatar`      VARCHAR(128)         DEFAULT NULL COMMENT '头像',
    `user_type`   CHAR(1)     NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
    `create_time` DATETIME             DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `del_flag`    INT(11)              DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表'