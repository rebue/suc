-- insert into SUC_HELLO (ID,HELLO,WORLD)VALUES(1,'Hello','world');
-- insert into SUC_HELLO (ID,HELLO,WORLD)VALUES(2,'你好','世界');

-- INSERT INTO CAPTCHA_QUESTION_BANK(ID,QUESTION,ANSWER,TIP)VALUES(1,'1+1=?','2','加法运算');
-- INSERT INTO CAPTCHA_QUESTION_BANK(ID,QUESTION,ANSWER,TIP)VALUES(2,'1+2=?','3','加法运算');
-- INSERT INTO CAPTCHA_QUESTION_BANK(ID,QUESTION,ANSWER,TIP)VALUES(3,'1+3=?','4','加法运算');
-- INSERT INTO CAPTCHA_QUESTION_BANK(ID,QUESTION,ANSWER,TIP)VALUES(4,'1+4=?','5','加法运算');
-- INSERT INTO CAPTCHA_QUESTION_BANK(ID,QUESTION,ANSWER,TIP)VALUES(5,'1+5=?','6','加法运算');

-- INSERT INTO CAPTCHA_EMAIL_TEMP(ID,TEMP_NAME,TEMP_CONTENT,EMAIL_SUBJECT)VALUES('REG','发送注册邮件的模板','您要注册的验证码是%s','注册邮件验证');
-- INSERT INTO CAPTCHA_EMAIL_TEMP(ID,TEMP_NAME,TEMP_CONTENT,EMAIL_SUBJECT)VALUES('RESET_PASSWORD','发送重置密码邮件的模板','您要重置密码的验证码是%s','重置密码邮件验证');

INSERT INTO SUC_USER (ID, LOGIN_NAME, LOGIN_PSWD, SALT, NICKNAME,MODIFIED_TIMESTAMP)VALUES(1,'admin','bc9db97ebf008d9acf54b333bd6f94ca','zcSeWA','平台管理员',UNIX_TIMESTAMP(SYSDATE()) * 1000);

insert into suc.SUC_DOMAIN (id,name,remark)values('buyer','买家','买家领域');
insert into suc.SUC_DOMAIN (id,name,remark)values('platform','平台','平台领域');
insert into suc.SUC_DOMAIN (id,name,remark)values('bussines','商家','商家领域');