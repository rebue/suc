-- 2018年9月19日 更新到大卖网线上
-- 在SUC_OP_LOG、SUC_LOGIN_LOG、SUC_REG等表中添加系统id和修改appid为非必填，在SUC_ORG中添加IS_ENABLED（是否启用）并设置默认值
alter table suc.SUC_OP_LOG add SYS_ID varchar(20) not null;
alter table suc.SUC_OP_LOG change APP_ID APP_ID tinyint;

alter table suc.SUC_LOGIN_LOG add SYS_ID varchar(20) not null;
alter table suc.SUC_LOGIN_LOG change APP_ID APP_ID tinyint;

alter table suc.SUC_REG add SYS_ID varchar(20) not null;
alter table suc.SUC_REG change APP_ID APP_ID tinyint;

create table SUC_ORG
(
   ID                   bigint not null comment '公司/组织id',
   NAME                 varchar(200) not null comment '公司/组织名称',
   REMARK               varchar(500) comment '公司/组织备注',
   CREATE_TIMESTAMP     bigint not null comment '创建时间戳',
   IS_ENABLED           bool not null default true comment '是否启用',
   primary key (ID)
);
alter table SUC_ORG comment '公司/组织信息';
alter table SUC_USER add constraint FK_Relationship_6 foreign key (ORG_ID)
      references SUC_ORG (ID) on delete restrict on update restrict;


-- 已更新线上
UPDATE SUC_USER a SET PROMOTER_ID = (SELECT PROMOTER_ID FROM SUC_REG WHERE ID = a.ID)


-- 1.0.8 在suc_user表中新加QQ、微信的openid和推广者id
alter table suc.SUC_USER add QQ_OPENID varchar(200);
alter table suc.SUC_USER add WX_OPENID varchar(200);
alter table suc.SUC_USER add PROMOTER_ID bigint;
alter table suc.SUC_USER add ORG_ID bigint;



-- ------------------------------------------------------------------ 以上已经更新到线上 ------------------------------------------------------------------ 
-- 2018-12-15
alter table SUC_USER add IS_TESTER            bool not null default false comment '是否测试者';


-- 2019-03-13
alter table SUC_ORG add CONTACT              varchar(15) comment '联系方式';

-- 2019-03-21
alter table SUC_ORG add SHORT_NAME           varchar(50) comment '公司/组织简称';

-- ------------------------------------------------------------------ 以上已经更新到线上 ------------------------------------------------------------------ 



--创建SUC_DOMAIN表
drop table if exists suc.SUC_DOMAIN;

create table suc.SUC_DOMAIN
(
   ID                   varchar(20) not null comment '领域ID',
   NAME                 varchar(20) not null comment '领域名称',
   REMARK               varchar(50) comment '领域备注',
   primary key (ID),
   unique key AK_DOMAIN_NAME (NAME)
);

alter table suc.SUC_DOMAIN comment '领域信息';
-- SUC_ORG表中添加组织编号字段
alter table suc.SUC_ORG add ORG_CODE varchar(30) comment '组织编号(也可称为商户号)';
-- SUC_ORG表中组织编号字段设为unique
alter table suc.SUC_ORG add unique (ORG_CODE);
-- SUC_USER表中添加领域id
alter table suc.SUC_USER add DOMAIN_ID varchar(20) comment '记录用户所属领域(也可称为群组)';
-- suc_user表中将领域id和组织id分别与登录账号、手机号、邮箱、微信id、QQid设为unique 
alter table suc.SUC_USER add unique key AK_DOMAIN_ID_AND_ORG_ID_AND_LOGIN_NAME (ORG_ID, LOGIN_NAME, DOMAIN_ID);
alter table suc.SUC_USER add unique key AK_DOMAIN_ID_AND_ORG_ID_AND_MOBILE (ORG_ID, DOMAIN_ID, MOBILE);
alter table suc.SUC_USER add unique key AK_DOMAIN_ID_AND_ORG_ID_AND_EMAIL (ORG_ID, EMAIL, DOMAIN_ID);
alter table suc.SUC_USER add unique key AK_DOMAIN_ID_AND_ORG_ID_AND_WX_ID (ORG_ID, WX_ID, DOMAIN_ID);
alter table suc.SUC_USER add unique key AK_DOMAIN_ID_AND_ORG_ID_AND_QQ_ID (ORG_ID, QQ_ID, DOMAIN_ID);
-- 添加外键
alter table suc.SUC_USER add constraint FK_Relationship_7 foreign key (DOMAIN_ID)
      references suc.SUC_DOMAIN (ID) on delete restrict on update restrict;
-- ------------------------------------------------------------------ 以上已经更新到线上 2019-06-27 ------------------------------------------------------------------ 
-- 删除旧的unique
ALTER TABLE SUC_USER DROP INDEX AK_USER_LOGIN_NAME;
ALTER TABLE SUC_USER DROP INDEX AK_USER_EMAIL;
ALTER TABLE SUC_USER DROP INDEX AK_USER_MOBILE;
ALTER TABLE SUC_USER DROP INDEX AK_USER_QQ_ID;
ALTER TABLE SUC_USER DROP INDEX AK_USER_WX_ID;
