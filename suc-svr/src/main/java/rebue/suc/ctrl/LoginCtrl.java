package rebue.suc.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rebue.jwt.svr.feign.JwtSvc;
import rebue.sbs.redis.RedisSetException;
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.suc.dic.LoginResultDic;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.svc.SucUserSvc;
import rebue.suc.to.LoginByLoginNameTo;
import rebue.suc.to.LoginByQqTo;
import rebue.suc.to.LoginByUserNameTo;
import rebue.suc.to.LoginByWxTo;
import rebue.wheel.AgentUtils;
import rebue.wheel.turing.JwtUtils;

@Api(tags = "用户登录")
@RestController
public class LoginCtrl {
    private final static Logger _log = LoggerFactory.getLogger(LoginCtrl.class);

    /**
     * 前面经过的代理
     */
    @Value("${suc.passProxy:noproxy}")
    private String              passProxy;

    @Resource
    private SucUserSvc          svc;
    @Resource
    private JwtSvc              jwtSvc;

    /**
     * 用户登录(登录名称)
     */
    @ApiOperation("用户通过登录名称(LoginName)登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-3: 密码错误;-4: 账号被锁定)")
    @PostMapping("/user/login/by/login/name")
    UserLoginRo loginByLoginName(@RequestBody LoginByLoginNameTo loginTo, HttpServletRequest req, HttpServletResponse resp) {
        _log.info("login: " + loginTo);
        loginTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        loginTo.setUserAgent(AgentUtils.getUserAgent(req));
        loginTo.setMac("不再获取MAC地址");
        UserLoginRo ro = svc.loginByLoginName(loginTo);
        if (LoginResultDic.SUCCESS.equals(ro.getResult())) {
            jwtSignWithCookie(ro, loginTo.getSysId(), resp);
        }
        return ro;
    }

    /**
     * 用户登录(用户名称)
     */
    @ApiOperation("用户通过用户名称(Email/Moblie/LoginName)登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-3: 密码错误;-4: 账号被锁定;-5: 用户用Email登录，但Email尚未通过验证;-6: 用户用手机号登录，但手机号尚未通过验证)")
    @PostMapping("/user/login/by/user/name")
    UserLoginRo loginByUserName(@RequestBody LoginByUserNameTo loginTo, HttpServletRequest req, HttpServletResponse resp) {
        _log.info("login: " + loginTo);
        loginTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        loginTo.setUserAgent(AgentUtils.getUserAgent(req));
        loginTo.setMac("不再获取MAC地址");
        UserLoginRo ro = svc.loginByUserName(loginTo);
        if (LoginResultDic.SUCCESS.equals(ro.getResult())) {
            jwtSignWithCookie(ro, loginTo.getSysId(), resp);
        }
        return ro;
    }

    /**
     * 用户登录(QQ)
     */
    @ApiOperation("用户通过QQ登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-4: 账号被锁定)")
    @PostMapping("/user/login/by/qq")
    UserLoginRo loginByQq(@RequestBody LoginByQqTo loginTo, HttpServletRequest req, HttpServletResponse resp) {
        _log.info("login: " + loginTo);
        loginTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        loginTo.setUserAgent(AgentUtils.getUserAgent(req));
        loginTo.setMac("不再获取MAC地址");
        UserLoginRo ro = svc.loginByQq(loginTo);
        if (LoginResultDic.SUCCESS.equals(ro.getResult())) {
            jwtSignWithCookie(ro, loginTo.getSysId(), resp);
        }
        return ro;
    }

    /**
     * 用户登录(微信)
     * 
     * @throws RedisSetException
     */
    @ApiOperation("用户通过微信登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-4: 账号被锁定)")
    @PostMapping("/user/login/by/wx")
    UserLoginRo loginByWx(@RequestBody LoginByWxTo loginTo, HttpServletRequest req, HttpServletResponse resp) throws RedisSetException {
        _log.info("login: " + loginTo);
        loginTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        loginTo.setUserAgent(AgentUtils.getUserAgent(req));
        loginTo.setMac("不再获取MAC地址");
        UserLoginRo ro = svc.loginByWx(loginTo);
        if (LoginResultDic.SUCCESS.equals(ro.getResult())) {
            jwtSignWithCookie(ro, loginTo.getSysId(), resp);
        }
        _log.info("微信登录的返回值为：{}", ro);
        return ro;
    }

    /**
     * JWT签名并将其加入Cookie
     */
    private void jwtSignWithCookie(UserLoginRo userLoginRo, String sysId, HttpServletResponse resp) {
        JwtSignRo signRo = jwtSvc.sign(userLoginRo.getUserId().toString(), sysId, userLoginRo.getOrgId());
        if (JwtSignResultDic.SUCCESS.equals(signRo.getResult())) {
            JwtUtils.addCookie(signRo.getSign(), signRo.getExpirationTime(), resp);
            userLoginRo.setSign(signRo.getSign());
            userLoginRo.setExpirationTime(signRo.getExpirationTime());
        }
    }

}
