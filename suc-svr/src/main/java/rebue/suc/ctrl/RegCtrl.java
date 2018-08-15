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
import rebue.scx.jwt.dic.JwtSignResultDic;
import rebue.scx.jwt.ro.JwtSignRo;
import rebue.suc.dic.RegResultDic;
import rebue.suc.ro.SucRegRo;
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
	private String passProxy;

	@Resource
	private SucUserSvc svc;
	@Resource
	private JwtSvc jwtSvc;

	/**
	 * 用户注册(登录名称)
	 */
	@ApiOperation("用户通过登录名称注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: QQ的ID已存在;-7: 微信的ID已存在)")
	@PostMapping("/user/reg/by/login/name")
	SucRegRo regByLoginName(@RequestBody RegByLoginNameTo regTo, HttpServletRequest req, HttpServletResponse resp) {
		SucRegRo regRo = new SucRegRo();
		_log.info("reg by login name: {}", regTo);
		regTo.setIp(AgentUtils.getIpAddr(req, passProxy));
		regTo.setUserAgent(AgentUtils.getUserAgent(req));
		regTo.setMac("不再获取MAC地址");
		UserRegRo ro = svc.regByLoginName(regTo);
		if (ro.getResult().equals(RegResultDic.SUCCESS)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("注册成功");
		} else if (ro.getResult().equals(RegResultDic.CACHE_FAIL)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("缓存失败");
		} else if (ro.getResult().equals(RegResultDic.PARAM_ERROR)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("参数不正确");
		} else if (ro.getResult().equals(RegResultDic.LOGIN_NAME_EXIST)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("该登录名称已存在");
		} else if (ro.getResult().equals(RegResultDic.EMAIL_EXIST)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("该邮箱已存在");
		} else if (ro.getResult().equals(RegResultDic.MOBILE_EXIST)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("该手机号码已存在");
		} else if (ro.getResult().equals(RegResultDic.IDCARD_EXIST)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("该身份证号已存在");
		} else if (ro.getResult().equals(RegResultDic.QQ_ID_EXIST)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("该QQ号码已存在");
		} else if (ro.getResult().equals(RegResultDic.WX_ID_EXIST)) {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("该微信号已存在");
		} else {
			regRo.setResult((byte) ro.getResult().getCode());
			regRo.setMsg("注册失败");
		}
		return regRo;
	}

	/**
	 * 用户注册(QQ)
	 */
	@ApiOperation("用户通过QQ注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: QQ的ID已存在;-7: 微信的ID已存在)")
	@PostMapping("/user/reg/by/qq")
	UserRegRo regByQq(@RequestBody RegByQqTo regTo, HttpServletRequest req, HttpServletResponse resp) {
		_log.info("reg by qq: {}", regTo);
		regTo.setIp(AgentUtils.getIpAddr(req, passProxy));
		regTo.setUserAgent(AgentUtils.getUserAgent(req));
		regTo.setMac("不再获取MAC地址");
		UserRegRo ro = svc.regByQq(regTo);
		if (RegResultDic.SUCCESS.equals(ro.getResult())) {
			jwtSignWithCookie(ro, resp);
		}
		return ro;
	}

	/**
	 * 用户注册(微信)
	 */
	@ApiOperation("用户通过微信注册\n(1: 成功;0: 缓存失败;-1: 参数不正确;-2: 用户登录名已存在;-3: Email已存在;-4: 手机号码已存在;-5: 身份证号码已存在;-6: 微信的ID已存在;-7: 微信的ID已存在)")
	@PostMapping("/user/reg/by/wx")
	UserRegRo regByWx(@RequestBody RegByWxTo regTo, HttpServletRequest req, HttpServletResponse resp) {
		_log.info("reg by wx: {}", regTo);
		regTo.setIp(AgentUtils.getIpAddr(req, passProxy));
		regTo.setUserAgent(AgentUtils.getUserAgent(req));
		regTo.setMac("不再获取MAC地址");
		UserRegRo ro = svc.regByWx(regTo);
		/*if (RegResultDic.SUCCESS.equals(ro.getResult())) {
			jwtSignWithCookie(ro, resp);
		}*/
		return ro;
	}

	/**
	 * JWT签名并将其加入Cookie
	 * 
	 * @param userId
	 *            用户ID
	 */
	private void jwtSignWithCookie(UserRegRo userRegRo, HttpServletResponse resp) {
		JwtSignRo signRo = jwtSvc.sign(userRegRo.getUserId().toString());
		if (JwtSignResultDic.SUCCESS.equals(signRo.getResult())) {
			JwtUtils.addCookie(signRo.getSign(), signRo.getExpirationTime(), resp);
			userRegRo.setSign(signRo.getSign());
			userRegRo.setExpirationTime(signRo.getExpirationTime());
		}
	}

}
