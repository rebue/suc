package rebue.suc.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.svc.SucUserSvc;

@Api(tags = "用户")
@RestController
public class PswdCtrl {
    private final static Logger _log = LoggerFactory.getLogger(PswdCtrl.class);

    @Resource
    private SucUserSvc          svc;

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
     * 微信设置登录密码
     * Title: setLoginPassword
     * Description: 
     * @param wxId
     * @param newLoginPswd
     * @return
     * @date 2018年5月2日 下午1:18:06
     */
    @PostMapping("/loginpswd/add/bywxid")
    Map<String, Object> setLoginPassword(String wxId, String newLoginPswd) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	try {
    		_log.info("设置登录密码的参数为：{}，{}", wxId, newLoginPswd);
			return svc.setLoginPassword(wxId, newLoginPswd);
		} catch (RuntimeException e) {
			String msg = e.getMessage();
			if (msg.equals("您未登陆，请先登录")) {
				resultMap.put("result", -1);
				resultMap.put("msg", msg);
			} else if (msg.equals("请输入新的登录密码")) {
				resultMap.put("result", -2);
				resultMap.put("msg", msg);
			} else if (msg.equals("找不到用户信息")) {
				resultMap.put("result", -3);
				resultMap.put("msg", msg);
			} else if (msg.equals("您已设置过登录密码")) {
				resultMap.put("result", -4);
				resultMap.put("msg", msg);
			} else if (msg.equals("设置失败")) {
				resultMap.put("result", -5);
				resultMap.put("msg", msg);
			} else {
				resultMap.put("result", -6);
				resultMap.put("msg", "设置失败");
			}
			_log.error(msg);
			return resultMap;
		}
	}
    
    /**
     * 修改登录密码
     * Title: changeLoginPassword
     * Description: 
     * @param wxId
     * @param oldLoginPswd
     * @param newLoginPswd
     * @return
     * @date 2018年5月2日 下午1:39:24
     */
    @PostMapping("/loginpswd/modify/bywxid")
    Map<String, Object> changeLoginPassword(String wxId, String oldLoginPswd, String newLoginPswd) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	try {
			return svc.changeLoginPassword(wxId, oldLoginPswd, newLoginPswd);
		} catch (RuntimeException e) {
			String msg = e.getMessage();
			if (msg.equals("您未登陆，请先登录")) {
				resultMap.put("result", -1);
				resultMap.put("msg", msg);
			} else if (msg.equals("请输入新的登录密码")) {
				resultMap.put("result", -2);
				resultMap.put("msg", msg);
			} else if (msg.equals("请输入旧的登录密码")) {
				resultMap.put("result", -3);
				resultMap.put("msg", msg);
			} else if (msg.equals("找不到用户信息")) {
				resultMap.put("result", -4);
				resultMap.put("msg", msg);
			} else if (msg.equals("输入的旧密码不正确")) {
				resultMap.put("result", -5);
				resultMap.put("msg", msg);
			} else if (msg.equals("修改失败")) {
				resultMap.put("result", -6);
				resultMap.put("msg", msg);
			} else if (msg.equals("您未设置登录密码，请先设置后再试")) {
				resultMap.put("result", -7);
				resultMap.put("msg", msg);
			} else {
				resultMap.put("result", -8);
				resultMap.put("msg", "修改失败");
			}
			return resultMap;
		}
    }
}
