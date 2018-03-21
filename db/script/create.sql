/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/2/5 8:48:59                             */
/*==============================================================*/


drop table if exists SUC_LOCK_LOG;

drop table if exists SUC_LOGIN_LOG;

drop table if exists SUC_OP_LOG;

drop table if exists SUC_REG;

drop table if exists SUC_USER;

drop table if exists SUC_USER_FORBIDDEN_WORD;

/*==============================================================*/
/* Table: SUC_LOCK_LOG                                          */
/*==============================================================*/
create table SUC_LOCK_LOG
(
   ID                   bigint not null comment '用户锁定日志ID',
   USER_ID              bigint not null comment '用户ID',
   LOCK_REASON          varchar(200) not null comment '锁定原因',
   LOCK_TIME            datetime not null comment '锁定时间',
   LOCK_OP_ID           bigint not null comment '锁定操作员ID，0为系统操作',
   UNLOCK_REASON        varchar(200) comment '解锁原因',
   UNLOCK_TIME          datetime comment '解锁时间',
   UNLOCK_OP_ID         bigint comment '解锁操作员ID，0为系统操作',
   primary key (ID)
);

alter table SUC_LOCK_LOG comment '用户锁定日志';

/*==============================================================*/
/* Table: SUC_LOGIN_LOG                                         */
/*==============================================================*/
create table SUC_LOGIN_LOG
(
   ID                   bigint not null comment '用户登录日志ID',
   USER_ID              bigint not null comment '用户ID',
   OP_TIME              datetime not null comment '操作时间',
   LOGIN_TYPE           tinyint not null comment '登录类型(与注册类型一致)
            1: 用户登录名
            2: 电子邮箱
            3: 手机
            4: QQ
            5: 微信',
   APP_ID               tinyint not null comment '应用ID
            标记是通过哪个应用系统登录的编码，要与注册的应用ID意义一致',
   USER_AGENT           varchar(500) not null comment '浏览器类型',
   IP                   varchar(150) not null comment 'IP地址',
   MAC                  varchar(30) not null comment 'MAC地址',
   primary key (ID)
);

alter table SUC_LOGIN_LOG comment '用户登录日志';

/*==============================================================*/
/* Table: SUC_OP_LOG                                            */
/*==============================================================*/
create table SUC_OP_LOG
(
   ID                   bigint not null comment '用户操作日志ID',
   USER_ID              bigint not null comment '用户ID',
   OP_TYPE              tinyint not null comment '操作类型
            1:修改登录密码;
            2:修改支付密码;
            3:绑定QQ登录;
            4:绑定微信登录;',
   OP_TIME              datetime not null comment '操作时间',
   OP_DETAIL			varchar(500) not null comment '操作详情',
   APP_ID               tinyint not null comment '应用ID
            标记是哪个应用系统来注册的编码，要与登录应用ID意义一致',
   USER_AGENT           varchar(500) not null comment '浏览器类型',
   MAC                  varchar(30) not null comment 'MAC地址',
   IP                   varchar(150) not null comment 'IP地址',
   primary key (ID)
);

alter table SUC_OP_LOG comment '用户操作日志';

/*==============================================================*/
/* Table: SUC_REG                                               */
/*==============================================================*/
create table SUC_REG
(
   ID                   bigint not null comment '用户ID',
   REG_TIME             datetime not null comment '注册时间',
   REG_TYPE             tinyint not null comment '注册类型(与登录类型一致)
                        1: 用户登录名
                        2: 电子邮箱
                        3: 手机
                        4: QQ
                        5: 微信',
   PROMOTER_ID          bigint comment '推广者ID',
   APP_ID               tinyint not null comment '应用ID
            标记是哪个应用系统来注册的编码，要与登录应用ID意义一致',
   USER_AGENT           varchar(500) not null comment '浏览器类型',
   MAC                  varchar(30) not null comment 'MAC地址',
   IP                   varchar(150) not null comment 'IP地址',
   primary key (ID)
);

alter table SUC_REG comment '用户注册';

/*==============================================================*/
/* Table: SUC_USER                                              */
/*==============================================================*/
create table SUC_USER
(
   ID                   bigint not null comment '用户ID',
   LOGIN_NAME           varchar(30) comment '登录账号',
   LOGIN_PSWD           varchar(32) comment '登录密码
            登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))',
   PAY_PSWD             varchar(32) comment '支付密码
            用户的支付密码默认和登录密码一致
            保存在字段的计算方法如下：
            MD5(数据库存储的已加密的登陆密码)',
   SALT                 char(6) comment '密码组合码
            与密码组合加密用
            登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))',
   NICKNAME             varchar(30) comment '昵称',
   FACE                 varchar(500) comment '头像',
   REALNAME             varchar(100) comment '实名',
   IS_VERIFIED_REALNAME bool default false comment '是否已验证实名',
   IDCARD               varchar(18) comment '身份证号',
   IS_VERIFIED_IDCARD   bool default false comment '是否已验证身份证号',
   EMAIL                varchar(150) comment '电子邮箱',
   IS_VERIFIED_EMAIL    bool default false comment '是否已验证电子邮箱',
   MOBILE               varchar(11) comment '手机',
   IS_VERIFIED_MOBILE   bool default false comment '是否已验证手机号码',
   QQ_ID                varchar(100) comment 'QQ的ID',
   QQ_NICKNAME          varchar(100) comment 'QQ昵称',
   QQ_FACE              varchar(500) comment 'QQ头像',
   WX_ID                varchar(100) comment '微信的ID',
   WX_NICKNAME          varchar(100) comment '微信昵称',
   WX_FACE              varchar(500) comment '微信头像',
   IS_LOCK              bool not null default false comment '是否锁定',
   MODIFIED_TIMESTAMP   bigint not null comment '修改时间戳(添加或更新本条记录时的时间戳)',
   primary key (ID),
   unique key AK_USER_LOGIN_NAME (LOGIN_NAME),
   unique key AK_USER_IDCARD (IDCARD),
   -- unique key AK_USER_EMAIL (EMAIL),
   unique key AK_USER_MOBILE (MOBILE),
   unique key AK_USER_QQ_ID (QQ_ID),
   unique key AK_USER_WX_ID (WX_ID)
);

alter table SUC_USER comment '用户信息';

/*==============================================================*/
/* Table: SUC_USER_FORBIDDEN_WORD                               */
/*==============================================================*/
create table SUC_USER_FORBIDDEN_WORD
(
   ID                   bigint not null comment '用户名敏感词ID',
   KEYWORD              varchar(20) not null comment '关键字',
   primary key (ID),
   unique key AK_USER_KEYWORD (KEYWORD)
);

alter table SUC_USER_FORBIDDEN_WORD comment '用户名敏感词
用户名敏感词汇信息表，就是注册登录账号不能用的词汇';

