-- 2018年8月22日12:59:54
-- 在SUC_OP_LOG、SUC_LOGIN_LOG、SUC_REG等表中添加系统id和修改appid为非必填，在SUC_ORG中添加IS_ENABLED（是否启用）并设置默认值
alter table suc.SUC_OP_LOG add SYS_ID varchar(20) not null;
alter table suc.SUC_OP_LOG change APP_ID APP_ID tinyint;

alter table suc.SUC_LOGIN_LOG add SYS_ID varchar(20) not null;
alter table suc.SUC_LOGIN_LOG change APP_ID APP_ID tinyint;

alter table suc.SUC_REG add SYS_ID varchar(20) not null;
alter table suc.SUC_REG change APP_ID APP_ID tinyint;

alter table suc.SUC_ORG add IS_ENABLED bool not null default 1;
alter table suc.SUC_ORG change CREATE_TIME CREATE_TIMESTAMP bigint not null;

-- 已更新线上
UPDATE SUC_USER a SET PROMOTER_ID = (SELECT PROMOTER_ID FROM SUC_REG WHERE ID = a.ID)


-- 1.0.8 在suc_user表中新加QQ、微信的openid和推广者id
alter table suc.SUC_USER add QQ_OPENID varchar(200);
alter table suc.SUC_USER add WX_OPENID varchar(200);
alter table suc.SUC_USER add PROMOTER_ID bigint;
alter table suc.SUC_USER add ORG_ID bigint;



