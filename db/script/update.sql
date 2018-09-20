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



