package rebue.suc.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import rebue.sbs.feign.FeignConfig;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.to.LoginByUserNameTo;

@FeignClient(name = "suc-svr", configuration = FeignConfig.class, contextId = "suc-svr-login")
public interface LoginSvc {

    /**
     * 用户登录(用户名称)
     */
    @ApiOperation("用户通过用户名称(Email/Moblie/LoginName)登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-3: 密码错误;-4: 账号被锁定;-5: 用户用Email登录，但Email尚未通过验证;-6: 用户用手机号登录，但手机号尚未通过验证)")
    @PostMapping("/user/login/by/user/name")
    UserLoginRo loginByUserName(@RequestBody LoginByUserNameTo loginTo);

    @ApiOperation("商家通过商家名称(Email/Moblie/LoginName)登录\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 找不到用户信息;-3: 密码错误;-4: 账号被锁定;-5: 用户用Email登录，但Email尚未通过验证;-6: 用户用手机号登录，但手机号尚未通过验证;-7: 用户不在此领域中)")
    @PostMapping("/user/login/by/business/name")
    UserLoginRo loginByBusinessName(@RequestBody final LoginByUserNameTo loginTo);
}
