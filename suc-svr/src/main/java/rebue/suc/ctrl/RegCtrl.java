package rebue.suc.ctrl;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

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
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.to.JwtUserInfoTo;
import rebue.suc.dic.RegResultDic;
import rebue.suc.ro.UserRegRo;
import rebue.suc.svc.SucUserSvc;
import rebue.suc.to.RegByLoginNameTo;
import rebue.suc.to.RegByQqTo;
import rebue.suc.to.RegByWxTo;
import rebue.wheel.AgentUtils;
import rebue.wheel.turing.JwtUtils;

@Api(tags = "用户注册")
@RestController
public class RegCtrl {
    private final static Logger _log = LoggerFactory.getLogger(RegCtrl.class);

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
     * 用户注册(登录名称)
     * 1: 成功;0: 缓存失败;
     * -1: 参数不正确;
     * -2: 用户登录名已存在;
     * -3: Email已存在;
     * -4: 手机号码已存在;
     * -5: 身份证号码已存在;
     * -6: QQ的ID已存在;
     * -7: 微信的ID已存在
     */
    @ApiOperation("用户通过登录名称注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: QQ的ID已存在;-7: 微信的ID已存在)")
    @PostMapping("/user/reg/by/login/name")
    UserRegRo regByLoginName(@RequestBody final RegByLoginNameTo regTo, final HttpServletRequest req, final HttpServletResponse resp) throws ParseException {
        _log.info("reg by login name: {}", regTo);
        regTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        regTo.setUserAgent(AgentUtils.getUserAgent(req));
//        if (regTo.getIsAddToCurOrg() != null && regTo.getIsAddToCurOrg()) {
        // 下一行在调试模式下直接设置当前组织，免得从cookie中获取麻烦
//        regTo.setOrgId(560754349274431488L);
        regTo.setOrgId((Long) JwtUtils.getJwtAdditionItemInCookie(req, "orgId"));
        final UserRegRo ro = svc.regByLoginName(regTo);
        if (RegResultDic.SUCCESS.equals(ro.getResult())) {
            jwtSignWithCookie(ro, regTo.getSysId(), null, resp);
        }
        return ro;
    }

    /**
     * 用户注册(QQ)
     */
    @ApiOperation("用户通过QQ注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: QQ的ID已存在;-7: 微信的ID已存在)")
    @PostMapping("/user/reg/by/qq")
    UserRegRo regByQq(@RequestBody final RegByQqTo regTo, final HttpServletRequest req, final HttpServletResponse resp) {
        _log.info("reg by qq: {}", regTo);
        regTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        regTo.setUserAgent(AgentUtils.getUserAgent(req));
        final UserRegRo ro = svc.regByQq(regTo);
        if (RegResultDic.SUCCESS.equals(ro.getResult())) {
            jwtSignWithCookie(ro, regTo.getSysId(), null, resp);
        }
        return ro;
    }

    /**
     * 用户注册(微信)
     */
    @ApiOperation("用户通过微信注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: 微信的ID已存在;-7: 微信的ID已存在)")
    @PostMapping("/user/reg/by/wx")
    UserRegRo regByWx(@RequestBody final RegByWxTo regTo, final HttpServletRequest req, final HttpServletResponse resp) {
        _log.info("reg by wx: {}", regTo);
        regTo.setIp(AgentUtils.getIpAddr(req, passProxy));
        regTo.setUserAgent(AgentUtils.getUserAgent(req));
        final UserRegRo ro = svc.regByWx(regTo);
        if (RegResultDic.SUCCESS.equals(ro.getResult())) {
            final Map<String, Object> addition = new LinkedHashMap<>();
            addition.put("wxOpenId", ro.getUserWxOpenId());
            addition.put("wxUnionId", ro.getUserWxUnionId());
            jwtSignWithCookie(ro, regTo.getSysId(), addition, resp);
        }
        return ro;
    }

    /**
     * JWT签名并将其加入Cookie
     * 
     * @param userId
     *            用户ID
     */
    private void jwtSignWithCookie(final UserRegRo userRegRo, final String sysId, Map<String, Object> addition, final HttpServletResponse resp) {
        if (addition == null) {
            addition = new LinkedHashMap<>();
        }
        addition.put("isTester", false);

        final JwtUserInfoTo to = new JwtUserInfoTo();
        to.setUserId(userRegRo.getUserId().toString());
        to.setSysId(sysId);
        to.setAddition(addition);
        final JwtSignRo signRo = jwtSvc.sign(to);
        if (JwtSignResultDic.SUCCESS.equals(signRo.getResult())) {
            JwtUtils.addCookie(signRo.getSign(), signRo.getExpirationTime(), resp);
            userRegRo.setSign(signRo.getSign());
            userRegRo.setExpirationTime(signRo.getExpirationTime());
        }
    }

}
