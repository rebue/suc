package rebue.suc.svc;

import java.util.Map;

import rebue.robotech.svc.MybatisBaseSvc;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.BindWxRo;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.ro.UserLoginRo;
import rebue.suc.ro.UserRegRo;
import rebue.suc.to.BindWxTo;
import rebue.suc.to.LoginByLoginNameTo;
import rebue.suc.to.LoginByQqTo;
import rebue.suc.to.LoginByUserNameTo;
import rebue.suc.to.LoginByWxTo;
import rebue.suc.to.RegByLoginNameTo;
import rebue.suc.to.RegByQqTo;
import rebue.suc.to.RegByWxTo;

public interface SucUserSvc extends MybatisBaseSvc<SucUserMo, java.lang.Long> {

    /**
     * 用户注册(通过登录名称)
     */
    UserRegRo regByLoginName(RegByLoginNameTo regTo);

    /**
     * 用户注册(通过QQ)
     */
    public UserRegRo regByQq(RegByQqTo regTo);

    /**
     * 用户注册(通过微信)
     */
    public UserRegRo regByWx(RegByWxTo regTo);

    /**
     * 用户登录(通过登录名称)
     */
    UserLoginRo loginByLoginName(LoginByLoginNameTo loginTo);

    /**
     * 用户登录(通过用户名称登录，按照 邮箱->手机->登录名 的顺序查找用户)
     */
    UserLoginRo loginByUserName(LoginByUserNameTo loginTo);

    /**
     * 用户登录(通过QQ登录)
     */
    UserLoginRo loginByQq(LoginByQqTo loginTo);

    /**
     * 用户登录(通过微信登录)
     */
    UserLoginRo loginByWx(LoginByWxTo loginTo);

    /**
     * 判断支付时是否需要输入密码
     * 
     * @param userId
     *            用户ID
     * @param amount
     *            金额(判断金额在一定数量下可以免密码输入)
     */
    Boolean requirePayPswd(Long userId, Double amount);

    /**
     * 校验支付密码
     * TODO 将参数参照verifyLoginPswdByUserName修改
     * 
     * @param userId
     *            用户ID
     * @param payPswd
     *            支付密码
     * @param amount
     *            支付金额(判断金额在一定数量下可以免密码输入)
     */
    PayPswdVerifyRo verifyPayPswd(Long userId, String payPswd, Double amount);

    /**
     * 判断用户是否被锁定
     */
    Boolean isLocked(Long id);

    /**
     * 用户绑定微信
     */
    BindWxRo bindWx(BindWxTo to);

    /**
     * 获取用户ID(通过用户名称)
     */
    Long getIdByUserName(String userName);

    /**
     * 获取用户ID(通过微信ID)
     */
    Long getIdByWxId(String wxId);

	/**
	 * 微信设置登录密码
	 * Title: setLoginPassword
	 * Description: 
	 * @param wxId
	 * @param newLoginPswd
	 * @return
	 * @date 2018年5月2日 下午1:10:03
	 */
	Map<String, Object> setLoginPassword(String wxId, String newLoginPswd);

	/**
	 * 微信修改登录密码
	 * Title: changeLoginPassword
	 * Description: 
	 * @param wxId
	 * @param oldLoginPswd
	 * @param newLoginPswd
	 * @return
	 * @date 2018年5月2日 下午1:21:46
	 */
	Map<String, Object> changeLoginPassword(String wxId, String oldLoginPswd, String newLoginPswd);

}