-- 1.0.8 在suc_user表中新加QQ、微信的openid和推广者id
alter table suc.SUC_USER add QQ_OPENID varchar(200);
alter table suc.SUC_USER add WX_OPENID varchar(200);
alter table suc.SUC_USER add PROMOTER_ID bigint;
alter table suc.SUC_USER add ORG_ID bigint;

UPDATE SUC_USER a SET PROMOTER_ID = (SELECT PROMOTER_ID FROM SUC_REG WHERE ID = a.ID)