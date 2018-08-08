-- 1.0.8 在suc_user表中新加QQ、微信的openid和推广者id
alter table suc.SUC_USER add QQ_OPENID varchar(200);
alter table suc.SUC_USER add WX_OPENID varchar(200);
alter table suc.SUC_USER add PROMOTER_ID bigint;
