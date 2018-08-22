package rebue.suc.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import rebue.sbs.feign.FeignConfig;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.PayPswdVerifyRo;

@FeignClient(name = "suc-svr", configuration = FeignConfig.class)
public interface SucUserSvc {

    /**
     * 根据用户ID获取用户信息
     */
    @GetMapping("/user/")
    SucUserMo getById(@RequestParam("id") Long id);

    /**
     * 判断用户是否存在
     */
    @GetMapping("/user/exist")
    Boolean exist(@RequestParam("id") Long id);

    /**
     * 判断用户是否被锁定
     */
    @GetMapping("/user/islocked")
    Boolean isLocked(@RequestParam("id") Long id);

    /**
     * 根据微信ID获取用户ID
     */
    @GetMapping("/user/id/bywxid")
    Long getIdByWxId(@RequestParam("wxId") String wxId);

    /**
     * 判断支付时是否需要输入密码
     * 
     * @param userId
     *            用户ID
     * @param amount
     *            金额(判断金额在一定数量下可以免密码输入)
     */
    @GetMapping("/paypswd/require")
    Boolean requirePayPswd(@RequestParam("userId") Long userId, @RequestParam("amount") Double amount);

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
            @RequestParam("amount") Double amount);

    /**
     * 根据id查询用户分页信息
     * @param pageNum
     * @param pageSize
     * @param ids
     * @return
     */
    @GetMapping("/suc/user/listuserbyids")
    PageInfo<SucUserMo> listUserByIds(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("ids") String ids);
}