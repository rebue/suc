package rebue.suc;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rebue.suc.dic.LoginResultDic;
import rebue.suc.dic.RegResultDic;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.ro.UserRegRo;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.turing.DigestUtils;

public class SucTests {
    private final static Logger _log     = LoggerFactory.getLogger(SucTests.class);

    private String              _hostUrl = "http://localhost:9100/";
//    private String              _hostUrl      = "http://192.168.1.201/suc-svr";
//    private String              _hostUrl      = "http://www.duamai.com/suc-svr";

    private ObjectMapper _objectMapper = new ObjectMapper();

    /**
     * 测试用户注册(通过登录名称注册)
     * 1. 没有填写必要参数
     * 2. 参数的格式不正确
     * 3. 张三三注册成功
     * 4. 登录名称已存在
     * 5. Email已存在
     * 6. Mobile已存在
     * 7. 身份证号码已存在
     * 8. 李四四注册成功
     * 9. 张三三是否被锁定
     */
    @Test
    public void test01() throws IOException {
        UserRegRo regRo;
        // 1. 没有填写必要参数
        regRo = regByLoginName(null, "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName(" ", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        regRo = regByLoginName("张三", null, null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三", " ", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", null, "用户浏览器A", "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());

        // 2. 参数的格式不正确
        regRo = regByLoginName("张三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "135458798581", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "1354587985", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "23545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "4501042015040110171", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "45010420150401101", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011016", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());

        // 3. 注册成功
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());
        Long promoterId = regRo.getUserId();

        // 4. 登录名称已存在
        regRo = regByLoginName("张三三", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.LOGIN_NAME_EXIST, regRo.getResult());

        // 5. Email已存在
        regRo = regByLoginName("李四四", "12345678", null, "zhangsan@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.EMAIL_EXIST, regRo.getResult());

        // 6. Mobile已存在
        regRo = regByLoginName("李四四", "12345678", null, "lisi@163.com", "13545879858", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.MOBILE_EXIST, regRo.getResult());

        // 7. 身份证号码已存在
        regRo = regByLoginName("李四四", "12345678", null, "lisi@163.com", "13545879857", "昵称A", "实名A", "450104201504011017", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.IDCARD_EXIST, regRo.getResult());

        // 8. 注册成功
        regRo = regByLoginName("李四四", "12345678", promoterId, "lisi@163.com", "13545879857", "昵称B", "实名B", "450104190001011014", "damai-wx", "用户浏览器B", "MAC地址B", "测试IPBS");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());
        regRo = regByLoginName("王五五", "12345678", promoterId, "wangwu@163.com", "13545879859", "昵称C", "实名C", "420921198212114647", "damai-wx", "用户浏览器C", "MAC地址C", "测试IPC");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());

        // 9. 张三三是否被锁定
        Assert.assertEquals(false, isLocked(promoterId));
    }

//    @Test
    public void test() throws JsonParseException, JsonMappingException, IOException {
        Long id = getIdByUserName("张三三");
        _log.info("得到用户张三三的ID:{}", id);
        _log.info("得到用户的信息:{}", getById(id));
    }

    /**
     * 获取用户ID(通过用户名称)
     */
    private Long getIdByUserName(String userName) throws JsonParseException, JsonMappingException, IOException {
        String url = _hostUrl + "/user/id/byusername";
        Map<String, Object> requestParams = new LinkedHashMap<>();
        requestParams.put("userName", userName);
        return _objectMapper.readValue(OkhttpUtils.get(url, requestParams), Long.class);
    }

    /**
     * 获取用户信息(通过用户ID)
     */
    private String getById(Long id) throws JsonParseException, JsonMappingException, IOException {
        String url = _hostUrl + "/user/";
        Map<String, Object> requestParams = new LinkedHashMap<>();
        requestParams.put("id", id.toString());
        return OkhttpUtils.get(url, requestParams);
    }

    private UserRegRo regByLoginName(String loginName, String loginPswd, Long promoterId, String email, String mobile, String nickname, String realname, String idcard,
            String sysId, String userAgent, String mac, String ip) throws IOException {
        String url = _hostUrl + "/user/reg/by/login/name";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (loginName != null)
            paramsMap.put("loginName", loginName);
        if (loginPswd != null)
            paramsMap.put("loginPswd", DigestUtils.md5AsHexStr(loginPswd.getBytes()));
        if (promoterId != null)
            paramsMap.put("promoterId", promoterId);
        if (email != null)
            paramsMap.put("email", email);
        if (mobile != null)
            paramsMap.put("mobile", mobile);
        if (nickname != null)
            paramsMap.put("nickname", nickname);
        if (realname != null)
            paramsMap.put("realname", realname);
        if (idcard != null)
            paramsMap.put("idcard", idcard);
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserRegRo.class);
    }

    /**
     * 判断用户是否被锁定
     */
    private Boolean isLocked(Long userId) throws JsonParseException, JsonMappingException, IOException {
        String url = _hostUrl + "/user/islocked";
        Map<String, Object> requestParams = new LinkedHashMap<>();
        requestParams.put("id", userId.toString());
        return _objectMapper.readValue(OkhttpUtils.get(url, requestParams), Boolean.class);
    }

    /**
     * 测试用户注册(通过QQ注册)
     * 1. 没有填写必要参数
     * 2. 注册成功
     * 3. QQ的ID已经存在
     */
    @Test
    public void test02() throws IOException {
        UserRegRo regRo;
        // 1. 没有填写必要参数
        regRo = regByQq(null, "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByQq("    ", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByQq("QQA的ID", null, "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByQq("QQA的ID", "    ", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByQq("QQA的ID", "QQ昵称A", null, null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByQq("QQA的ID", "QQ昵称A", "   ", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, null, "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());

        // 2. 注册成功
        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());
        Long promoterId = regRo.getUserId();

        // 3. QQ的ID已经存在
        regRo = regByQq("QQA的ID", "QQ昵称A", "QQ头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.QQ_ID_EXIST, regRo.getResult());

        // 4. 注册成功
        regRo = regByQq("QQB的ID", "QQ昵称B", "QQ头像B", promoterId, "damai-wx", "用户浏览器B", "MAC地址B", "IP地址B");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());
    }

    private UserRegRo regByQq(String qqId, String qqNickname, String qqFace, Long promoterId, String sysId, String userAgent, String mac, String ip) throws IOException {
        String url = _hostUrl + "/user/reg/by/qq";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (qqId != null)
            paramsMap.put("qqId", qqId);
        if (qqNickname != null)
            paramsMap.put("qqNickname", qqNickname);
        if (qqFace != null)
            paramsMap.put("qqFace", qqFace);
        if (promoterId != null)
            paramsMap.put("promoterId", promoterId);
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserRegRo.class);
    }

    /**
     * 测试用户注册(通过微信注册)
     * 1. 没有填写必要参数
     * 2. 注册成功
     * 3. 微信的ID已经存在
     */
    @Test
    public void test03() throws IOException {
        UserRegRo regRo;
        // 1. 没有填写必要参数
//        regRo = regByWx(null, "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("    ", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", null, "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "    ", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, null, "用户浏览器A", "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());
//        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(RegResultDic.PARAM_ERROR, regRo.getResult());

        // 2. 注册成功
        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());
        Long promoterId = regRo.getUserId();

        // 3. 微信的ID已经存在
        regRo = regByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(RegResultDic.WX_ID_EXIST, regRo.getResult());

        // 4. 注册成功
        regRo = regByWx("微信B的ID", "微信B的OPEN_ID", "微信昵称B", "微信头像B", promoterId, "damai-wx", "用户浏览器B", "MAC地址B", "IP地址B");
        Assert.assertEquals(RegResultDic.SUCCESS, regRo.getResult());
    }

    private UserRegRo regByWx(String wxId, String wxOpenId, String wxNickname, String wxFace, Long promoterId, String sysId, String userAgent, String mac, String ip)
            throws IOException {
        String url = _hostUrl + "/user/reg/by/wx";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (wxId != null)
            paramsMap.put("wxId", wxId);
        if (wxOpenId != null)
            paramsMap.put("wxOpenid", wxOpenId);
        if (wxNickname != null)
            paramsMap.put("wxNickname", wxNickname);
        if (wxFace != null)
            paramsMap.put("wxFace", wxFace);
        if (promoterId != null)
            paramsMap.put("promoterId", promoterId);
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserRegRo.class);
    }

    /**
     * 测试用户登录(通过登录名称登录)
     * 1. 没有填写必要参数
     * 2. 找不到用户信息
     * 3. 密码错误
     * 4. 超过5次输入密码错误，账号被锁定
     * 5. 登录成功
     */
    @Test
    public void test04() throws IOException {
        UserLoginRo loginRo;
        // 1. 没有填写必要参数
        loginRo = loginByLoginName(null, "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByLoginName(" ", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", " ", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", "12345678", null, "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByLoginName("李四四", "12345678", "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByLoginName("李四四", "12345678", "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByLoginName("李四四", "12345678", "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByLoginName("李四四", "12345678", "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByLoginName("李四四", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByLoginName("李四四", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());

        // 4. 找不到用户信息
        loginRo = loginByLoginName("李四四四", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.NOT_FOUND_USER, loginRo.getResult());

        // 5. 密码错误
        loginRo = loginByLoginName("李四四", "12345676", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", "12345679", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", "123456789", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", "1234567", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByLoginName("李四四", "1234567899", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());

        // 6. 超过5次输入密码错误，账号被锁定
        loginRo = loginByLoginName("李四四", "1234567899", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.LOCKED, loginRo.getResult());

        // 7. 登录成功
        loginRo = loginByLoginName("王五五", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.SUCCESS, loginRo.getResult());

    }

    private UserLoginRo loginByLoginName(String loginName, String loginPswd, String sysId, String userAgent, String mac, String ip) throws IOException {
        String url = _hostUrl + "/user/login/by/login/name";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (loginName != null)
            paramsMap.put("loginName", loginName);
        if (loginPswd != null) {
            if (StringUtils.isBlank(loginPswd)) {
                paramsMap.put("loginPswd", loginPswd);
            } else {
                paramsMap.put("loginPswd", DigestUtils.md5AsHexStr(loginPswd.getBytes()));
            }
        }
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserLoginRo.class);
    }

    /**
     * 测试用户登录(通过用户名称登录)
     * 1. 没有填写必要参数
     * 2. Email尚未通过验证
     * 3. 手机号尚未通过验证
     * 4. 找不到用户信息
     * 5. 密码错误
     * 6. 超过5次输入密码错误，账号被锁定
     * 7. 登录成功
     */
    @Test
    public void test05() throws IOException {
        UserLoginRo loginRo;
        // 1. 没有填写必要参数
        loginRo = loginByUserName(null, "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByUserName(" ", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", " ", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", "12345678", null, "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByUserName("张三三", "12345678", "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByUserName("张三三", "12345678", "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByUserName("张三三", "12345678", "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByUserName("张三三", "12345678", "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByUserName("张三三", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByUserName("张三三", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());

        // 2. Email尚未通过验证
        loginRo = loginByUserName("zhangsan@163.com", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.NO_VERITY_EMAIL, loginRo.getResult());

        // 3. 手机号尚未通过验证
        loginRo = loginByUserName("13545879858", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.NO_VERITY_MOBILE, loginRo.getResult());

        // 4. 找不到用户信息
        loginRo = loginByUserName("张三三三", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.NOT_FOUND_USER, loginRo.getResult());
        loginRo = loginByUserName("zhangsan1@163.com", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.NOT_FOUND_USER, loginRo.getResult());
        loginRo = loginByUserName("13545879860", "12345678", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.NOT_FOUND_USER, loginRo.getResult());

        // 5. 密码错误
        loginRo = loginByUserName("张三三", "12345676", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", "12345679", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", "123456789", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", "1234567", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());
        loginRo = loginByUserName("张三三", "1234567899", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PASSWORD_ERROR, loginRo.getResult());

        // 6. 超过5次输入密码错误，账号被锁定
        loginRo = loginByUserName("张三三", "1234567899", "damai-wx", "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.LOCKED, loginRo.getResult());

        // 7. 登录成功
        loginRo = loginByUserName("王五五", "12345678", "damai-wx", "用户浏览器C", "MAC地址C", "测试IPC");
        Assert.assertEquals(LoginResultDic.SUCCESS, loginRo.getResult());

    }

    private UserLoginRo loginByUserName(String userName, String loginPswd, String sysId, String userAgent, String mac, String ip) throws IOException {
        String url = _hostUrl + "/user/login/by/user/name";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (userName != null)
            paramsMap.put("userName", userName);
        if (loginPswd != null) {
            if (StringUtils.isBlank(loginPswd)) {
                paramsMap.put("loginPswd", loginPswd);
            } else {
                paramsMap.put("loginPswd", DigestUtils.md5AsHexStr(loginPswd.getBytes()));
            }
        }
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserLoginRo.class);
    }

    /**
     * 测试用户登录(通过QQ登录)
     * 1. 没有填写必要参数
     * 2. QQ的ID已经存在
     * 3. 登录成功
     */
    @Test
    public void test06() throws IOException {
        UserLoginRo loginRo;
        // 1. 没有填写必要参数
        loginRo = loginByQq(null, "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByQq("    ", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", null, "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "    ", "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", null, "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "   ", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", null, "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());

        // 2. 登录成功
        loginRo = loginByQq("QQA的ID", "QQA的OPEN_ID", "QQ昵称A", "QQ头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.SUCCESS, loginRo.getResult());
        loginRo = loginByQq("QQB的ID", "QQB的OPEN_ID", "QQ昵称B", "QQ头像B", "damai-wx", "用户浏览器B", "MAC地址B", "IP地址B");
        Assert.assertEquals(LoginResultDic.SUCCESS, loginRo.getResult());

        // 3. QQ的ID不存在
        loginRo = loginByQq("QQC的ID", "QQC的OPEN_ID", "QQ昵称C", "QQ头像C", "damai-wx", "用户浏览器C", "MAC地址C", "IP地址C");
        Assert.assertEquals(LoginResultDic.NOT_FOUND_USER, loginRo.getResult());
    }

    private UserLoginRo loginByQq(String qqId, String qqOpenid, String qqNickname, String qqFace, String sysId, String userAgent, String mac, String ip) throws IOException {
        String url = _hostUrl + "/user/login/by/qq";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (qqId != null)
            paramsMap.put("qqId", qqId);
        if (qqOpenid != null)
            paramsMap.put("qqOpenid", qqOpenid);
        if (qqNickname != null)
            paramsMap.put("qqNickname", qqNickname);
        if (qqFace != null)
            paramsMap.put("qqFace", qqFace);
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserLoginRo.class);
    }

    /**
     * 测试用户登录(通过微信登录)
     * 1. 没有填写必要参数
     * 2. 微信的ID已经存在
     * 3. 登录成功
     */
    @Test
    public void test07() throws IOException {
        UserLoginRo loginRo;
        // 1. 没有填写必要参数
        loginRo = loginByWx(null, "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByWx("    ", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", null, "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "    ", "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", null, "用户浏览器A", "MAC地址A", "测试IP");
        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", null, "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "    ", "MAC地址A", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", null, "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", "   ", "测试IP");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", null);
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());
//        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", "   ");
//        Assert.assertEquals(LoginResultDic.PARAM_ERROR, loginRo.getResult());

        // 2. 登录成功
        loginRo = loginByWx("微信A的ID", "微信A的OPEN_ID", "微信昵称A", "微信头像A", "damai-wx", "用户浏览器A", "MAC地址A", "IP地址A");
        Assert.assertEquals(LoginResultDic.SUCCESS, loginRo.getResult());
        loginRo = loginByWx("微信B的ID", "微信B的OPEN_ID", "微信昵称B", "微信头像B", "damai-wx", "用户浏览器B", "MAC地址B", "IP地址B");
        Assert.assertEquals(LoginResultDic.SUCCESS, loginRo.getResult());

        // 3. 微信的ID不存在
        loginRo = loginByWx("微信C的ID", "微信C的OPEN_ID", "微信昵称C", "微信头像C", "damai-wx", "用户浏览器C", "MAC地址C", "IP地址C");
        Assert.assertEquals(LoginResultDic.NOT_FOUND_USER, loginRo.getResult());
    }

    private UserLoginRo loginByWx(String wxId, String wxOpenid, String wxNickname, String wxFace, String sysId, String userAgent, String mac, String ip) throws IOException {
        String url = _hostUrl + "/user/login/by/wx";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        if (wxId != null)
            paramsMap.put("wxId", wxId);
        if (wxOpenid != null)
            paramsMap.put("wxOpenid", wxOpenid);
        if (wxNickname != null)
            paramsMap.put("wxNickname", wxNickname);
        if (wxFace != null)
            paramsMap.put("wxFace", wxFace);
        if (sysId != null)
            paramsMap.put("sysId", sysId);
        if (userAgent != null)
            paramsMap.put("userAgent", userAgent);
        if (mac != null)
            paramsMap.put("mac", mac);
        if (ip != null)
            paramsMap.put("ip", ip);
        return _objectMapper.readValue(OkhttpUtils.postByJsonParams(url, paramsMap), UserLoginRo.class);
    }

//    @Test
    public void setLoginName() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wxId", "ov3CM0udTraWqJbsWlBNqqicM-w");
        map.put("loginName", "名字不要太长这样就好了");
        String result = OkhttpUtils.postByJsonParams(_hostUrl + "user/setloginname/bywxid", map);
        System.out.println(result);
    }
}
