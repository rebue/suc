package rebue.suc.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import rebue.sbs.feign.FeignConfig;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.CurrentUserRo;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.ro.SucRegRo;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.to.LoginByUserNameTo;

@FeignClient(name = "suc-svr", configuration = FeignConfig.class)
public interface SucUserSvc {

    /**
     * 根据用户ID获取用户信息
     */
    @GetMapping("/user/")
    SucUserMo getById(@RequestParam("id") Long id);

    /**
     * 用户登录(用户名称)
     */
    @PostMapping(value = "/user/login/by/user/name", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserLoginRo loginByUserName(@RequestBody LoginByUserNameTo loginTo);

    /**
     * 获取当前用户
     */
    @GetMapping("/user/currentuser")
    CurrentUserRo getCurrentUser();

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
    PayPswdVerifyRo verifyPayPswd(@RequestParam("userId") Long userId, @RequestParam("payPswd") String payPswd, @RequestParam("amount") Double amount);

    /**
     * 根据id查询用户分页信息
     * 
     * @param pageNum
     * @param pageSize
     * @param ids
     * @return
     */
    @GetMapping("/suc/user/listuserbyids")
    PageInfo<SucUserMo> listUserByIds(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("ids") String ids);

    /**
     * 获取用户购买关系
     */
    @GetMapping("/user/getBuyRelation")
    String getBuyRelation(@RequestParam("userId") Long userId, @RequestParam("onlineId") Long onlineId);

    /**
     * 获取单个用户注册信息
     */
    @GetMapping("/suc/reg/getbyid")
    SucRegRo getRegInfo(@RequestParam("id") Long id);

    /**
     * 模糊查询关键字且在指定多个用户ID范围内的用户列表
     * 
     * @param keys
     *            模糊查询用户的关键字
     * @param userIds
     *            用户ID列表的字符串，用逗号隔开
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @GetMapping("/suc/user/listbykeysanduserids")
    PageInfo<SucUserDetailRo> listByKeysAndUserIds(@RequestParam(value = "keys", required = false) String keys, @RequestParam("userIds") String userIds,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * 模糊查询关键字且排除指定多个用户ID外的用户列表
     * 
     * @param keys
     *            模糊查询用户的关键字
     * @param userIds
     *            用户ID列表的字符串，用逗号隔开
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @GetMapping("/suc/user/listbykeysandnotuserids")
    PageInfo<SucUserDetailRo> listByKeysAndNotUserIds(@RequestParam(value = "keys", required = false) String keys, @RequestParam("userIds") String userIds,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize);

}