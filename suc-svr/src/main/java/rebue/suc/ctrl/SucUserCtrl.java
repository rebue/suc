package rebue.suc.ctrl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.CurrentUserRo;
import rebue.suc.ro.GetLoginNameRo;
import rebue.suc.ro.SetLoginNameRo;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.SucUserRo;
import rebue.suc.svc.SucUserSvc;
import rebue.wheel.turing.JwtUtils;

@RestController
public class SucUserCtrl {

    /**
     * 有唯一约束的字段名称
     *
     * @mbg.generated
     */
    private final String _uniqueFilesName = "某字段内容";

    /**
     * 添加用户信息
     *
     * @mbg.generated
     */
    @PostMapping("/suc/user")
    SucUserRo add(@RequestBody final SucUserMo mo) throws Exception {
        _log.info("add SucUserMo:" + mo);
        final SucUserRo ro = new SucUserRo();
        try {
            final int result = svc.add(mo);
            if (result == 1) {
                final String msg = "添加成功";
                _log.info("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) 1);
                return ro;
            } else {
                final String msg = "添加失败";
                _log.error("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) -1);
                return ro;
            }
        } catch (final DuplicateKeyException e) {
            final String msg = "添加失败，" + _uniqueFilesName + "已存在，不允许出现重复";
            _log.error("{}: mo-{}", msg, mo);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        }
    }

    /**
     * 删除用户信息
     *
     * @mbg.generated
     */
    @DeleteMapping("/suc/user")
    SucUserRo del(@RequestParam("id") final java.lang.Long id) {
        _log.info("save SucUserMo:" + id);
        final int result = svc.del(id);
        final SucUserRo ro = new SucUserRo();
        if (result == 1) {
            final String msg = "删除成功";
            _log.info("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) 1);
            return ro;
        } else {
            final String msg = "删除失败，找不到该记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        }
    }

    private static final Logger _log = LoggerFactory.getLogger(SucUserCtrl.class);

    @Resource
    private SucUserSvc          svc;

    @Value("${debug:false}")
    private Boolean             isDebug;

    /**
     * 获取用户信息（通过用户ID）
     * 
     * @mbg.overrideByMethodName
     */
    @GetMapping("/user/")
    SucUserMo getById(@RequestParam("id") final Long id) {
        _log.info("getById: id-{}", id);
        return svc.getById(id);
    }

    /**
     * 判断用户是否存在
     */
    @GetMapping("/user/exist")
    Boolean exist(@RequestParam("id") final Long id) {
        _log.info("判断用户是否存在: {}", id);
        return svc.existByPrimaryKey(id);
    }

    /**
     * 判断用户是否被锁定
     */
    @GetMapping("/user/islocked")
    Boolean isLocked(@RequestParam("id") final Long id) {
        _log.info("判断用户是否被锁定: {}", id);
        return svc.isLocked(id);
    }

    /**
     * 判断用户是否是测试用户
     */
    @GetMapping("/user/istester")
    Boolean isTester(@RequestParam("userId") final Long userId) {
        _log.info("isTester: {}", userId);
        return svc.isTester(userId);
    }

    /**
     * 获取用户ID(通过用户名称)
     */
    @GetMapping("/user/id/byusername")
    Long getIdByUserName(@RequestParam("userName") final String userName) {
        _log.info("获取用户ID(通过用户名称): {}", userName);
        return svc.getIdByUserName(userName);
    }

    /**
     * 获取用户ID(通过微信ID)
     */
    @GetMapping("/user/id/bywxid")
    Long getIdByWxId(@RequestParam("wxId") final String wxId) {
        _log.info("获取用户ID(通过微信ID): wxId-{}", wxId);
        return svc.getIdByWxId(wxId);
    }

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
    PageInfo<SucUserDetailRo> listByKeysAndUserIds(@RequestParam(value = "keys", required = false) final String keys,
            @RequestParam(value = "userIds", required = false) final String userIds, @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 7;
        }
        _log.info("list ByKeysAndUserIds: userIds=" + userIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return svc.listByKeysAndUserIds(keys, userIds, pageNum, pageSize);
    }

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
    PageInfo<SucUserDetailRo> listByKeysAndNotUserIds(@RequestParam(value = "keys", required = false) final String keys,
            @RequestParam(value = "userIds", required = false) final String userIds, @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 7;
        }
        _log.info("list ByKeysAndNotUserIds: userIds=" + userIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return svc.listByKeysAndNotUserIds(keys, userIds, pageNum, pageSize);
    }

    /**
     * 通过微信ID设置登录名称 Title: setLoginName Description:
     *
     * @param wxId
     * @param loginName
     * @return
     * @date 2018年5月3日 下午6:09:55
     */
    @PostMapping("/user/setloginname/bywxid")
    SetLoginNameRo setLoginName(@RequestParam("id") final Long id, @RequestParam("loginName") final String loginName) {
        _log.info("通过ID设置登录名称: {}", id);
        return svc.setLoginName(id, loginName);
    }

    /**
     * 通过Id查询用户的登录密码是否存在
     *
     * @param wxId
     * @param loginName
     * @return
     * @date 2018年5月3日 下午6:09:55
     */
    @GetMapping("/user/loginPwIsExis")
    String loginPwIsExis(@RequestParam("id") final Long id) {
        _log.info("通过用户ID查看用户登录密码是否存在参数为: {}", id);
        final SucUserMo result = svc.getById(id);
        if (result.getLoginPswd() != null) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 通过Id查询用户的登录密码是否存在
     *
     * @param wxId
     * @param loginName
     * @return
     * @date 2018年5月3日 下午6:09:55
     */
    @GetMapping("/user/payPwIsExis")
    String payPwIsExis(@RequestParam("id") final Long id) {
        _log.info("通过用户ID查看用户登录密码是否存在参数为: {}", id);
        final SucUserMo result = svc.getById(id);
        if (result.getPayPswd() != null) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 根据微信ID获取用户登录名称
     *
     * @param wxId
     * @return
     * @date 2018年5月4日 上午9:06:34
     */
    @GetMapping("/user/loginName/bywxid")
    GetLoginNameRo getLoginNameByWx(@RequestParam("id") final Long id) {
        _log.info("根据ID获取用户登录名称: {}", id);
        return svc.getLoginNameByWx(id);
    }

//    /**
//     * 查询用户信息
//     * 
//     * @mbg.overrideByMethodName
//     */
//    @GetMapping("/suc/user")
//    PageInfo<UserPointRo> list(@RequestParam(value = "keys", required = false) final String keys, @RequestParam(value = "isOrgGet", required = false) final Boolean isOrgGet,
//            @RequestParam("pageNum") final int pageNum, @RequestParam("pageSize") final int pageSize, final HttpServletRequest request) throws ParseException {
//        _log.info("list users:" + keys + ", isOrgGet=" + isOrgGet + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
//        if (pageSize > 50) {
//            final String msg = "pageSize不能大于50";
//            _log.error(msg);
//            throw new IllegalArgumentException(msg);
//        }
//        Long orgId = null;
//        if (isOrgGet != null && isOrgGet) {
//            orgId = 560754349274431488L;
//            if (!isDebug) {
//                orgId = (Long) JwtUtils.getJwtAdditionItemInCookie(request, "orgId");
//            }
//        }
//
//        final PageInfo<UserPointRo> result = svc.listEx(keys, orgId, pageNum, pageSize);
//        _log.info("result: " + result);
//        return result;
//    }

    /**
     * 蚂蚁根据ｉｄ获取单条记录的方式
     *
     * @param id
     * @return
     * @mbg.overrideByMethodName
     */
    @GetMapping("/suc/user/getbyid")
    SucUserRo getByIdEx(@RequestParam("id") final java.lang.Long id) {
        final SucUserMo result = svc.getById(id);
        _log.info("get: " + result);
        final SucUserRo ro = new SucUserRo();
        if (result == null) {
            final String msg = "获取失败，没有找到该条记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        } else {
            final String msg = "获取成功";
            _log.info("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) 1);
            ro.setRecord(result);
            return ro;
        }
    }

    /**
     * 修改用户信息
     *
     * @param mo
     * @return
     * @mbg.overrideByMethodName
     */
    @PutMapping("/suc/user")
    SucUserRo modify(@RequestBody final SucUserMo mo) {
        _log.info("修改用户信息的参数为：{}", mo);
        return svc.modifyEx(mo);
    }

    /**
     * 设置用户登录密码
     *
     * @param mo
     * @return
     * @mbg.overrideByMethodName
     */
    @PutMapping("/suc/user/setloginpw")
    SucUserRo setLoginPw(@RequestBody final SucUserMo mo) {
        _log.info("修改用户登录密码的参数：{}", mo);
        return svc.setLoginPw(mo);
    }

    /**
     * 设置禁用或解锁用户
     *
     * @param id
     * @param isLock
     * @return
     */
    @PutMapping("/suc/user/enable")
    SucUserRo enable(@RequestBody final SucUserMo mo) {
        _log.info("设置禁用或解锁用户的参数为：{}", mo);
        return svc.enable(mo.getId(), mo.getIsLock());
    }

    /**
     * 解除登录密码
     *
     * @param id
     * @return
     */
    @PutMapping("/suc/user/del/loginpassword")
    SucUserRo removeLoginPassWord(@RequestParam("id") final Long id) {
        _log.info("解除用户登录密码的参数为：{}", id);
        return svc.removeLoginPassWord(id);
    }

    /**
     * 解除支付密码
     *
     * @param id
     * @return
     */
    @PutMapping("/suc/user/del/paypassword")
    SucUserRo removePayPassWord(@RequestParam("id") final Long id) {
        _log.info("解除用户支付密码的参数为：{}", id);
        return svc.removeLoginPassWord(id);
    }

    /**
     * 解绑微信
     *
     * @param mo
     * @return
     */
    @PutMapping("/suc/user/unbindwechat")
    SucUserRo unbindWeChat(@RequestParam("id") final Long id) {
        _log.info("解绑微信的参数为：{}", id);
        return svc.unbindWeChat(id);
    }

    /**
     * 解绑QQ
     *
     * @param mo
     * @return
     */
    @PutMapping("/suc/user/unbindqq")
    SucUserRo unbindQQ(@RequestParam("id") final Long id) {
        _log.info("解绑微信的参数为：{}", id);
        return svc.unbindQQ(id);
    }

    /**
     * 根据id查询用户分页信息
     * 
     * @param pageNum
     * @param pageSize
     * @param ids
     * @return
     */
    @GetMapping("/suc/user/listuserbyidsAndKeys")
    PageInfo<SucUserMo> listUserByIdsAndKeys(@RequestParam("pageNum") final int pageNum, @RequestParam("pageSize") final int pageSize, @RequestParam("userIds") final String ids,
            @RequestParam(value = "addedKeys", required = false) final String addedKeys) {
        _log.info("查询用户信息的参数为：pageNum={}, pageSize={}, ids={},kdys={}", pageNum, pageSize, ids, addedKeys);
        return svc.listUserByIdsAndKeys(pageNum, pageSize, ids, addedKeys);
    }

    /**
     * 根据组织查询用户信息
     */
    @GetMapping("/suc/user/listbyorgid")
    PageInfo<SucUserMo> listByOrgId(@RequestParam("orgId") final Long orgId, @RequestParam("pageNum") final int pageNum, @RequestParam("pageSize") final int pageSize) {
        _log.info("查询用户信息的参数为：{}, pageNum={}, pageSize={}", orgId, pageNum, pageSize);
        final SucUserMo mo = new SucUserMo();
        mo.setOrgId(orgId);
        return svc.list(mo, pageNum, pageSize);
    }

    /**
     * 添加用户组织
     */
    @PutMapping("/suc/user/adduserorg")
    SucUserRo addUserOrg(@RequestParam("id") final Long id, @RequestParam("orgId") final Long orgId) {
        _log.info("添加用户组织的请求参数为：{}, {}", id, orgId);
        return svc.addUserOrg(id, orgId);
    }

    /**
     * 删除用户组织
     */
    @PutMapping("/suc/user/deluserorgbyid")
    SucUserRo delUserOrgById(@RequestParam("id") final Long id) {
        _log.info("删除用户组织的参数为：{}", id);
        return svc.delUserOrgById(id);
    }

    /**
     * 获取当前用户信息
     * 
     * @throws ParseException
     */
    @GetMapping("/user/currentuser")
    CurrentUserRo getCurrentUser(final HttpServletRequest req) throws ParseException {
        _log.info("获取当前用户信息");
        // 从签名中获取用户ID
        final Long userId = JwtUtils.getJwtUserIdInCookie(req);
        // 通过用户ID获取用户信息
        return svc.getCurrentUser(userId);
    }

    /**
     * 根据用户微信昵称查找用户信息
     */
    @GetMapping("/user/getByWxNick")
    List<SucUserMo> getByWxNick(@RequestParam("wxNickName") final String wxNickName) {
        _log.info("根据微信昵称获取用户信息参数：{}", wxNickName);
        final SucUserMo mo = new SucUserMo();
        mo.setWxNickname(wxNickName);
        return svc.list(mo);
    }

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
    @GetMapping("/suc/user/listunaddedusersbyorgidandusers")
    PageInfo<SucUserDetailRo> listUnaddedUsersByOrgIdAndUsers(@RequestParam(value = "orgId", required = false) final Long orgId,
            @RequestParam(value = "userIds", required = false) final String userIds, @RequestParam(value = "keys", required = false) final String keys,
            @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 7;
        }
        _log.info("list ByKeysAndNotUserIds: userIds=" + userIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return svc.listUnaddedUsersByOrgIdAndUsers(orgId, userIds, keys, pageNum, pageSize);
    }

    /**
     * 根据条件查询一条用户信息
     * 
     * @param mo
     * @return
     */
    @GetMapping("/suc/user/getone")
    SucUserMo getOne(final SucUserMo mo) {
        _log.info("getOne SucUserMo-{}", mo);
        return svc.getOne(mo);
    }

    /**
     * 根据领域id查询用户
     * 
     * @param domainId
     * @return
     */
    @GetMapping("/suc/user/selectByDomainId")
    List<SucUserMo> selectByDomainId(final String domainId) {
        _log.info("selectByDomainId domainId-{}", domainId);
        return svc.selectByDomainId(domainId);
    }

    /**
     * 根据买家创建商家用户信息
     */
    @PutMapping("/suc/user/installByBuyer")
    int installById(@RequestBody final SucUserMo mo) {
        _log.info("updateById SucUserMo-{}", mo);
        return svc.installByBuyer(mo);
    }

    /**
     * 根据领域id分页查询用户信息
     * 
     * @param domainId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/suc/user/list-by-domain")
    PageInfo<SucUserMo> listByDomain(@RequestParam("domainId") final String domainId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        _log.info("查询用户信息的参数为：{}, pageNum={}, pageSize={}", domainId, pageNum, pageSize);
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        final SucUserMo mo = new SucUserMo();
        mo.setDomainId(domainId);
        return svc.list(mo, pageNum, pageSize);
    }

    /**
     * 根据领域id和关键字查询用户信息
     * 
     * @param domainId
     * @param keys
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/suc/user/list-by-domain-and-keys")
    PageInfo<SucUserMo> listUserByDomainIdAndKeys(@RequestParam("domainId") final String domainId, @RequestParam(value = "keys", required = false) final String keys,
            @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        _log.info("查询用户信息的参数为：{}, pageNum={}, pageSize={},keys={}", domainId, pageNum, pageSize, keys);
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        if (pageSize > 50) {
            final String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return svc.listUserByDomainIdAndKeys(pageNum, pageSize, domainId, keys);
    }
}
