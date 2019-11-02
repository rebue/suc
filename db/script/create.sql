/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/2 16:40:48                           */
/*==============================================================*/


drop table if exists CAPTCHA_EMAIL_TEMP;

drop table if exists CAPTCHA_QUESTION_BANK;

drop table if exists SUC_DOMAIN;

drop table if exists SUC_HELLO;

drop table if exists SUC_LOCK_LOG;

drop table if exists SUC_LOGIN_LOG;

drop table if exists SUC_OP_LOG;

drop table if exists SUC_ORG;

drop table if exists SUC_REG;

drop index SUC_USER_AK7 on SUC_USER;

drop index SUC_USER_AK5 on SUC_USER;

drop index SUC_USER_AK4 on SUC_USER;

drop index SUC_USER_AK3 on SUC_USER;

drop table if exists SUC_USER;

drop table if exists SUC_USER_FORBIDDEN_WORD;

/*==============================================================*/
/* Table: CAPTCHA_EMAIL_TEMP                                    */
/*==============================================================*/
create table CAPTCHA_EMAIL_TEMP
(
   ID                   national varchar(30) not null comment '邮箱模板ID',
   TEMP_NAME            national varchar(20) not null comment '模板名称',
   TEMP_CONTENT         national varchar(500) not null comment '模板内容',
   EMAIL_SUBJECT        national varchar(50) not null comment '邮件主题',
   primary key (ID),
   key AK_TEMP_NAME (TEMP_NAME)
);

/*==============================================================*/
/* Table: CAPTCHA_QUESTION_BANK                                 */
/*==============================================================*/
create table CAPTCHA_QUESTION_BANK
(
   ID                   bigint not null comment '验证码题库ID',
   QUESTION             national varchar(200) not null comment '验证问题',
   ANSWER               national varchar(200) not null comment '问题答案',
   TIP                  national varchar(200) comment '验证提示',
   primary key (ID)
);

/*==============================================================*/
/* Table: SUC_DOMAIN                                            */
/*==============================================================*/
create table SUC_DOMAIN
(
   ID                   varchar(20) not null comment '领域ID',
   NAME                 varchar(20) not null comment '领域名称',
   REMARK               varchar(50) comment '领域备注',
   primary key (ID),
   key AK_DOMAIN_NAME (NAME)
);

alter table SUC_DOMAIN comment '领域信息';

/*==============================================================*/
/* Table: SUC_HELLO                                             */
/*==============================================================*/
create table SUC_HELLO
(
   ID                   bigint not null comment '测试ID',
   HELLO                varchar(20) comment 'HELLO',
   WORLD                varchar(40) comment 'WORLD',
   primary key (ID)
);

/*==============================================================*/
/* Table: SUC_LOCK_LOG                                          */
/*==============================================================*/
create table SUC_LOCK_LOG
(
   ID                   bigint not null comment '用户锁定日志ID',
   USER_ID              bigint not null comment '用户ID',
   LOCK_REASON          varchar(100) not null comment '锁定原因',
   LOCK_TIME            datetime not null comment '锁定时间',
   LOCK_OP_ID           bigint not null comment '锁定操作员ID，0为系统操作',
   UNLOCK_REASON        varchar(100) comment '解锁原因',
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
   APP_ID               tinyint comment '应用ID
            标记是通过哪个应用系统登录的编码，要与注册的应用ID意义一致',
   USER_AGENT           varchar(500) not null comment '浏览器类型',
   IP                   varchar(150) not null comment 'IP地址',
   MAC                  varchar(30) comment 'MAC地址',
   SYS_ID               varchar(20) not null comment '系统id',
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
   OP_DETAIL            varchar(300) not null comment '操作详情',
   OP_TIME              datetime not null comment '操作时间',
   APP_ID               tinyint comment '应用ID
            标记是哪个应用系统来注册的编码，要与登录应用ID意义一致',
   USER_AGENT           varchar(500) not null comment '浏览器类型',
   MAC                  varchar(30) not null comment 'MAC地址',
   SYS_ID               varchar(20) not null comment '系统id',
   IP                   varchar(150) not null comment 'IP地址',
   primary key (ID)
);

alter table SUC_OP_LOG comment '用户操作日志';

/*==============================================================*/
/* Table: SUC_ORG                                               */
/*==============================================================*/
create table SUC_ORG
(
   ID                   bigint not null comment '公司/组织id',
   NAME                 varchar(200) not null comment '公司/组织名称',
   SHORT_NAME           varchar(50) comment '公司/组织简称',
   REMARK               varchar(500) comment '公司/组织备注',
   CREATE_TIMESTAMP     bigint not null comment '创建时间戳',
   IS_ENABLED           bool not null default true comment '是否启用',
   CONTACT              varchar(15) comment '联系方式',
   ORG_CODE             varchar(30) comment '组织编号(也可称为商户号)',
   primary key (ID),
   key AK_ORG_CODE (ORG_CODE)
);

alter table SUC_ORG comment '公司/组织信息';

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
   APP_ID               tinyint comment '应用ID
            标记是哪个应用系统来注册的编码，要与登录应用ID意义一致',
   USER_AGENT           varchar(500) not null comment '浏览器类型',
   MAC                  varchar(30) comment 'MAC地址',
   IP                   varchar(150) not null comment 'IP地址',
   SYS_ID               varchar(20) not null comment '系统id',
   primary key (ID)
);

alter table SUC_REG comment '用户注册';

/*==============================================================*/
/* Table: SUC_USER                                              */
/*==============================================================*/
create table SUC_USER
(
   ID                   bigint not null comment '用户ID',
   ORG_ID               bigint comment '公司/组织id',
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
   QQ_ID                varchar(200) comment 'QQ的ID',
   QQ_OPENID            varchar(200) comment 'QQ的openid',
   QQ_NICKNAME          varchar(100) comment 'QQ昵称',
   QQ_FACE              varchar(500) comment 'QQ头像',
   WX_ID                varchar(200) comment '微信的ID',
   WX_OPENID            varchar(200) comment '微信openid',
   WX_NICKNAME          varchar(100) comment '微信昵称',
   WX_FACE              varchar(500) comment '微信头像',
   IS_TESTER            bool not null default false comment '是否测试者',
   IS_LOCK              bool not null default false comment '是否锁定',
   PROMOTER_ID          bigint comment '推广者ID',
   DOMAIN_ID            varchar(20) comment '记录用户所属领域(也可称为群组）',
   MODIFIED_TIMESTAMP   bigint not null comment '修改时间戳',
   primary key (ID),
   unique key AK_USER_QQ_OPENID (QQ_OPENID),
   unique key AK_USER_WX_OPENID (WX_OPENID),
   key AK_DOMAIN_ID_AND_ORG_ID_AND_LOGIN_NAME (ORG_ID, LOGIN_NAME, DOMAIN_ID),
   key AK_DOMAIN_ID_AND_ORG_ID_AND_MOBILE (ORG_ID, DOMAIN_ID, MOBILE),
   key AK_DOMAIN_ID_AND_ORG_ID_AND_EMAIL (ORG_ID, EMAIL, DOMAIN_ID),
   key AK_DOMAIN_ID_AND_ORG_ID_AND_WX_ID (ORG_ID, WX_ID, DOMAIN_ID),
   key AK_DOMAIN_ID_AND_ORG_ID_AND_QQ_ID (ORG_ID, QQ_ID, DOMAIN_ID)
);

alter table SUC_USER comment '用户信息';

/*==============================================================*/
/* Index: SUC_USER_AK3                                          */
/*==============================================================*/
create unique index SUC_USER_AK3 on SUC_USER
(
   EMAIL
);

/*==============================================================*/
/* Index: SUC_USER_AK4                                          */
/*==============================================================*/
create unique index SUC_USER_AK4 on SUC_USER
(
   MOBILE
);

/*==============================================================*/
/* Index: SUC_USER_AK5                                          */
/*==============================================================*/
create unique index SUC_USER_AK5 on SUC_USER
(
   QQ_ID
);

/*==============================================================*/
/* Index: SUC_USER_AK7                                          */
/*==============================================================*/
create unique index SUC_USER_AK7 on SUC_USER
(
   WX_ID
);

/*==============================================================*/
/* Table: SUC_USER_FORBIDDEN_WORD                               */
/*==============================================================*/
create table SUC_USER_FORBIDDEN_WORD
(
   ID                   bigint not null comment '用户名敏感词ID',
   KEYWORD              varchar(20) not null comment '关键字',
   primary key (ID),
   key AK_USER_KEYWORD (KEYWORD)
);

alter table SUC_USER_FORBIDDEN_WORD comment '用户名敏感词
用户名敏感词汇信息表，就是注册登录账号不能用的词汇';

alter table SUC_LOCK_LOG add constraint FK_LOCK_USER foreign key (USER_ID)
      references SUC_USER (ID) on delete restrict on update restrict;

alter table SUC_LOGIN_LOG add constraint FK_LOGIN_USER foreign key (USER_ID)
      references SUC_USER (ID) on delete restrict on update restrict;

alter table SUC_OP_LOG add constraint FK_OP_USER foreign key (USER_ID)
      references SUC_USER (ID) on delete restrict on update restrict;

alter table SUC_REG add constraint FK_Relationship_4 foreign key (ID)
      references SUC_USER (ID) on delete restrict on update restrict;

alter table SUC_REG add constraint FK_Relationship_5 foreign key (PROMOTER_ID)
      references SUC_USER (ID) on delete restrict on update restrict;

alter table SUC_USER add constraint FK_Relationship_6 foreign key (ORG_ID)
      references SUC_ORG (ID) on delete restrict on update restrict;

alter table SUC_USER add constraint FK_Relationship_7 foreign key (DOMAIN_ID)
      references SUC_DOMAIN (ID) on delete restrict on update restrict;

