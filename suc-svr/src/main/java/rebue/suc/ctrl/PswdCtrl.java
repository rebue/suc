package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rebue.suc.ro.LoginPswdModifyRo;
import rebue.suc.ro.LoginPswdSetRo;
import rebue.suc.ro.PayPswdModifyRo;
import rebue.suc.ro.PayPswdSetRo;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.svc.SucUserSvc;

@Api(tags = "用户")
@RestController
public class PswdCtrl {
	private final static Logger _log = LoggerFactory.getLogger(PswdCtrl.class);

	@Resource
	private SucUserSvc svc;

	/**
	 * 判断支付时是否需要输入密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param amount
	 *            金额(判断金额在一定数量下可以免密码输入)
	 */
	@GetMapping("/paypswd/require")
	Boolean requirePayPswd(@RequestParam("userId") Long userId, @RequestParam("amount") Double amount) {
		_log.info("判断支付时是否需要输入密码: userId-{},amount-{}", userId, amount);
		return svc.requirePayPswd(userId, amount);
	}

	/**
	 * 校验支付密码
	 * 
	 * @param userId
	 *            用户ID
	 * @param payPswd
	 *            支付密码
	 * @param amount
	 *            支付金额(判断金额在一定数量下可以免密码输入)
	 */
	@GetMapping("/paypswd/verify")
	PayPswdVerifyRo verifyPayPswd(@RequestParam("userId") Long userId, @RequestParam("payPswd") String payPswd,
			@RequestParam("amount") Double amount) {
		_log.info("校验支付密码: userId-{},payPswd-{},amount-{}", userId, payPswd, amount);
		return svc.verifyPayPswd(userId, payPswd, amount);
	}

	/**
	 * 设置登录密码
	 */
	@PostMapping("/loginpswd/add/bywxid")
	LoginPswdSetRo setLoginPassword(@RequestParam("id") Long id,
			@RequestParam("newLoginPswd") String newLoginPswd) {
		_log.info("设置登录密码的参数为：{}，{}", id, newLoginPswd);
		return svc.setLoginPassword(id, newLoginPswd);
	}

	/**
	 * 修改登录密码
	 */
	@PostMapping("/loginpswd/modify/bywxid")
	LoginPswdModifyRo changeLoginPassword(@RequestParam("id") Long id,
			@RequestParam("oldLoginPswd") String oldLoginPswd, @RequestParam("newLoginPswd") String newLoginPswd) {
		return svc.changeLoginPassword(id, oldLoginPswd, newLoginPswd);
	}
	
	/**
	 * 微信设置支付密码
	 */
	@PostMapping("/paypswd/add/bywxid")
	PayPswdSetRo setPayPassword(@RequestParam("id") Long id,
			@RequestParam("newPayPswd") String newPayPswd) {
		_log.info("设置登录密码的参数为：{}，{}", id, newPayPswd);
		return svc.setPayPassword(id, newPayPswd);
	}

	/**
	 * 修改登录密码
	 */
	@PostMapping("/paypswd/modify/bywxid")
	PayPswdModifyRo changePayPassword(@RequestParam("id") Long id,
			@RequestParam("oldPayPswd") String oldPayPswd, @RequestParam("newPayPswd") String newPayPswd) {
		return svc.changePayPassword(id, oldPayPswd, newPayPswd);
	}
}
