package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rebue.suc.ro.UserRegRo;
import rebue.suc.svc.SucUserSvc;
import rebue.suc.to.RegByLoginNameTo;
import rebue.suc.to.RegByQqTo;
import rebue.suc.to.RegByWxTo;

@Api(tags = "用户注册")
@RestController
public class RegCtrl {
    private final static Logger _log = LoggerFactory.getLogger(RegCtrl.class);

    @Resource
    private SucUserSvc          svc;

    /**
     * 用户注册(登录名称)
     */
    @ApiOperation("用户通过登录名称注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: QQ的ID已存在;-7: 微信的ID已存在)")
    @PostMapping("/user/reg/by/login/name")
    UserRegRo regByLoginName(@RequestBody RegByLoginNameTo regTo) {
        _log.info("reg by login name: {}", regTo);
        return svc.regByLoginName(regTo);
    }

    /**
     * 用户注册(QQ)
     */
    @ApiOperation("用户通过QQ注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: QQ的ID已存在;-7: 微信的ID已存在)")
    @PostMapping("/user/reg/by/qq")
    UserRegRo regByQq(@RequestBody RegByQqTo regTo) {
        _log.info("reg by qq: {}", regTo);
        return svc.regByQq(regTo);
    }

    /**
     * 用户注册(微信)
     */
    @ApiOperation("用户通过微信注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: 微信的ID已存在;-7: 微信的ID已存在)")
    @PostMapping("/user/reg/by/wx")
    UserRegRo regByWx(@RequestBody RegByWxTo regTo) {
        _log.info("reg by wx: {}", regTo);
        return svc.regByWx(regTo);
    }

}
