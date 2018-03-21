package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
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

}
