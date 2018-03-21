package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.svc.SucUserSvc;
import rebue.suc.to.LoginByLoginNameTo;
import rebue.suc.to.LoginByQqTo;
import rebue.suc.to.LoginByUserNameTo;
import rebue.suc.to.LoginByWxTo;

@Api(tags = "用户登录")
@RestController
public class LoginCtrl {
    private final static Logger _log = LoggerFactory.getLogger(LoginCtrl.class);

    @Resource
    private SucUserSvc          svc;

    /**
     * 用户登录(登录名称)
     */
    @ApiOperation("用户通过登录名称(LoginName)登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-3: 密码错误;-4: 账号被锁定)")
    @PostMapping("/user/login/by/login/name")
    UserLoginRo loginByLoginName(LoginByLoginNameTo loginTo) {
        _log.info("login: " + loginTo);
        return svc.loginByLoginName(loginTo);
    }

    /**
     * 用户登录(用户名称)
     */
    @ApiOperation("用户通过用户名称(Email/Moblie/LoginName)登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-3: 密码错误;-4: 账号被锁定;-5: 用户用Email登录，但Email尚未通过验证;-6: 用户用手机号登录，但手机号尚未通过验证)")
    @PostMapping("/user/login/by/user/name")
    UserLoginRo loginByUserName(LoginByUserNameTo loginTo) {
        _log.info("login: " + loginTo);
        return svc.loginByUserName(loginTo);
    }

    /**
     * 用户登录(QQ)
     */
    @ApiOperation("用户通过QQ登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-4: 账号被锁定)")
    @PostMapping("/user/login/by/qq")
    UserLoginRo loginByQq(LoginByQqTo loginTo) {
        _log.info("login: " + loginTo);
        return svc.loginByQq(loginTo);
    }

    /**
     * 用户登录(微信)
     */
    @ApiOperation("用户通过微信登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-4: 账号被锁定)")
    @PostMapping("/user/login/by/wx")
    UserLoginRo loginByWx(LoginByWxTo loginTo) {
        _log.info("login: " + loginTo);
        return svc.loginByWx(loginTo);
    }

}
