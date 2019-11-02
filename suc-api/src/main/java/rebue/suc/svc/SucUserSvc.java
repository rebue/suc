package rebue.suc.svc;

import java.util.List;

import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.MybatisBaseSvc;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.BindWxRo;
import rebue.suc.ro.CurrentUserRo;
import rebue.suc.ro.GetLoginNameRo;
import rebue.suc.ro.LoginPswdModifyRo;
import rebue.suc.ro.LoginPswdSetRo;
import rebue.suc.ro.PayPswdModifyRo;
import rebue.suc.ro.PayPswdSetRo;
import rebue.suc.ro.PayPswdVerifyRo;
import rebue.suc.ro.SetLoginNameRo;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.SucUserRo;
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
    UserRegRo regByQq(RegByQqTo regTo);

    /**
     * 用户注册(通过微信)
     */
    UserRegRo regByWx(RegByWxTo regTo);

    /**
     * 用户登录(通过登录名称)
     */
    UserLoginRo loginByLoginName(LoginByLoginNameTo loginTo);

    /**
     * 用户登录(通过用户名称登录，按照 邮箱->手机->登录名 的顺序查找用户)
     */
    UserLoginRo loginByUserName(LoginByUserNameTo loginTo);

//    /**
//     * 商家登录(通过用户名称登录，按照 邮箱->手机->登录名 的顺序查找用户)
//     */
//    UserLoginRo loginByBusinessName(LoginByUserNameTo loginTo);

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
     * 校验支付密码 TODO 将参数参照verifyLoginPswdByUserName修改
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
    Boolean isLocked(Long userId);

    /**
     * 判断用户是否是测试用户
     */
    Boolean isTester(Long userId);

    /**
     * 用户绑定微信
     */
    BindWxRo bindWx(BindWxTo to);

    /**
     * 获取用户ID(通过用户名称)
     */
    Long getIdByUserName(String domainId, Long orgId, String userName);

    /**
     * 获取用户ID(通过微信ID)
     */
    Long getIdByWxId(String domainId, Long orgId, String wxId, String wxOpenId);

    /**
     * 微信设置登录密码 Title: setLoginPassword Description:
     *
     * @param id
     * @param newLoginPswd
     * @return
     * @date 2018年5月2日 下午1:10:03
     */
    LoginPswdSetRo setLoginPassword(Long id, String newLoginPswd);

    /**
     * 微信修改登录密码 Title: changeLoginPassword Description:
     *
     * @param id
     * @param oldLoginPswd
     * @param newLoginPswd
     * @return
     * @date 2018年5月2日 下午1:21:46
     */
    LoginPswdModifyRo changeLoginPassword(Long id, String oldLoginPswd, String newLoginPswd);

    /**
     * 根据微信ID设置登录名称 Title: setLoginName Description:
     *
     * @param id
     * @param loginName
     * @return
     * @date 2018年5月3日 下午5:38:56
     */
    SetLoginNameRo setLoginName(Long id, String loginName);

    /**
     * 根据微信ID获取用户登录名称 Title: selectLoginNameByWx Description:
     *
     * @param wxId
     * @return
     * @date 2018年5月4日 上午9:04:49
     */
    GetLoginNameRo getLoginNameByWx(Long id);

    /**
     * 修改用户信息
     *
     * @param mo
     * @return
     */
    SucUserRo modifyEx(SucUserMo mo);

    /**
     * 设置用户登录密码
     *
     * @param mo
     * @return
     */
    SucUserRo setLoginPw(SucUserMo mo);

    /**
     * 禁用或者解锁用户
     *
     * @param id
     * @param isLock
     * @return
     */
    SucUserRo enable(Long id, Boolean isLock);

    /**
     * 解除登录密码
     *
     * @param id
     * @return
     */
    SucUserRo removeLoginPassWord(Long id);

    /**
     * 解除支付密码
     *
     * @param id
     * @return
     */
    SucUserRo removePayPassWord(Long id);

    /**
     * 解绑微信
     *
     * @param id
     * @return
     */
    SucUserRo unbindWeChat(Long id);

    /**
     * 解绑QQ
     *
     * @param id
     * @return
     */
    SucUserRo unbindQQ(Long id);

//    /**
//     * 多条件同时查询
//     * 
//     * @param users
//     * @param pageNum
//     * @param pageSize
//     * @return
//     */
//    PageInfo<UserPointRo> listEx(String keys, Long orgId, int pageNum, int pageSize);

    /**
     * 添加用户组织
     * 
     * @param id
     * @param orgId
     * @return
     */
    SucUserRo addUserOrg(Long id, Long orgId);

    /**
     * 删除用户组织
     * 
     * @param id
     * @return
     */
    SucUserRo delUserOrgById(Long id);

    /**
     * 根据用户id查询用户分页信息
     * 
     * @param pageNum
     * @param pageSize
     * @param ids
     * @return
     */
    PageInfo<SucUserMo> listUserByIdsAndKeys(int pageNum, int pageSize, String ids, String keys);

    /**
     * 获取当前用户信息
     */
    CurrentUserRo getCurrentUser(Long userId);

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
    PageInfo<SucUserDetailRo> listByKeysAndUserIds(String keys, String userIds, Integer pageNum, Integer pageSize);

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
    PageInfo<SucUserDetailRo> listByKeysAndNotUserIds(String keys, String userIds, Integer pageNum, Integer pageSize);

    /**
     * 设置支付密码
     * 
     * @param id
     * @param newPayPswd
     * @return
     */
    PayPswdSetRo setPayPassword(Long id, String newPayPswd);

    /**
     * 修改支付密码
     * 
     * @param id
     * @param oldPayPswd
     * @param newPayPswd
     * @return
     */
    PayPswdModifyRo changePayPassword(Long id, String oldPayPswd, String newPayPswd);

    /**
     * 根据组织id、用户id、关键字查询除指定id外的用户列表
     * 
     * @param orgId
     *            组织id
     * @param userIds
     *            要排除的用户，多个以逗号隔开
     * @param keys
     *            模糊查询的用户关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     * @return
     */
    PageInfo<SucUserDetailRo> listUnaddedUsersByOrgIdAndUsers(Long orgId, String userIds, String keys, Integer pageNum, Integer pageSize);

    /**
     * 根据领域id查询用户信息
     * 
     * @param mo
     * @return
     */
    List<SucUserMo> selectByDomainId(String domainId);

    /**
     * 根据买家创建商家用户信息
     * 
     * @param mo
     * @return
     */
    int installByBuyer(SucUserMo mo);

    /**
     * 根据领域id和关键字查询用户信息
     * 
     * @param pageNum
     * @param pageSize
     * @param domainId
     * @param keys
     * @return
     */
    PageInfo<SucUserMo> listUserByDomainIdAndKeys(int pageNum, int pageSize, String domainId, String keys);

}
