package rebue.suc.ctrl;
import java.text.ParseException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private String _uniqueFilesName = "某字段内容";

    /**
     * 添加用户信息
     *
     * @mbg.generated
     */
    @PostMapping("/suc/user")
    SucUserRo add(@RequestBody SucUserMo mo) throws Exception {
        _log.info("add SucUserMo:" + mo);
        SucUserRo ro = new SucUserRo();
        try {
            int result = svc.add(mo);
            if (result == 1) {
                String msg = "添加成功";
                _log.info("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) 1);
                return ro;
            } else {
                String msg = "添加失败";
                _log.error("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) -1);
                return ro;
            }
        } catch (DuplicateKeyException e) {
            String msg = "添加失败，" + _uniqueFilesName + "已存在，不允许出现重复";
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
    SucUserRo del(@RequestParam("id") java.lang.Long id) {
        _log.info("save SucUserMo:" + id);
        int result = svc.del(id);
        SucUserRo ro = new SucUserRo();
        if (result == 1) {
            String msg = "删除成功";
            _log.info("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) 1);
            return ro;
        } else {
            String msg = "删除失败，找不到该记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        }
    }

    private static final Logger _log = LoggerFactory.getLogger(SucUserCtrl.class);

    @Resource
    private SucUserSvc          svc;

    /**
     * 获取用户信息（通过用户ID）
     * 
     * @mbg.overrideByMethodName
     */
    @GetMapping("/user/")
    SucUserMo getById(@RequestParam("id") Long id) {
        _log.info("getById: id-{}", id);
        return svc.getById(id);
    }

    /**
     * 判断用户是否存在
     */
    @GetMapping("/user/exist")
    Boolean exist(@RequestParam("id") Long id) {
        _log.info("判断用户是否存在: {}", id);
        return svc.existByPrimaryKey(id);
    }

    /**
     * 判断用户是否被锁定
     */
    @GetMapping("/user/islocked")
    Boolean isLocked(@RequestParam("id") Long id) {
        _log.info("判断用户是否被锁定: {}", id);
        return svc.isLocked(id);
    }

    /**
     * 获取用户ID(通过用户名称)
     */
    @GetMapping("/user/id/byusername")
    Long getIdByUserName(@RequestParam("userName") String userName) {
        _log.info("获取用户ID(通过用户名称): {}", userName);
        return svc.getIdByUserName(userName);
    }

    /**
     * 获取用户ID(通过微信ID)
     */
    @GetMapping("/user/id/bywxid")
    Long getIdByWxId(@RequestParam("wxId") String wxId) {
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
    PageInfo<SucUserDetailRo> listByKeysAndUserIds(@RequestParam(value = "keys", required = false) String keys, @RequestParam("userIds") String userIds,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 7;
        _log.info("list ByKeysAndUserIds: userIds=" + userIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
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
    PageInfo<SucUserDetailRo> listByKeysAndNotUserIds(@RequestParam(value = "keys", required = false) String keys, @RequestParam("userIds") String userIds,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 7;
        _log.info("list ByKeysAndNotUserIds: userIds=" + userIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
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
    SetLoginNameRo setLoginName(@RequestParam("wxId") String wxId, @RequestParam("loginName") String loginName) {
        _log.info("通过微信ID设置登录名称: {}", wxId);
        return svc.setLoginName(wxId, loginName);
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
    String loginPwIsExis(@RequestParam("id") Long id) {
        _log.info("通过用户ID查看用户登录密码是否存在参数为: {}", id);
        SucUserMo result= svc.getById(id);
        if(result.getLoginPswd()!=null) {
        	return "1";
        }else {
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
    GetLoginNameRo getLoginNameByWx(@RequestParam("wxId") String wxId) {
        _log.info("根据微信ID获取用户登录名称: {}", wxId);
        return svc.getLoginNameByWx(wxId);
    }

    /**
     * 查询用户信息
     *
     * @mbg.overrideByMethodName
     */
    @GetMapping("/suc/user")
    PageInfo<SucUserMo> list(@RequestParam(value = "users", required = false) String users, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        _log.info("list users:" + users + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        PageInfo<SucUserMo> result = svc.listEx(users, pageNum, pageSize);
        _log.info("result: " + result);
        return result;
    }

    /**
     * 蚂蚁根据ｉｄ获取单条记录的方式
     *
     * @param id
     * @return
     * @mbg.overrideByMethodName
     */
    @GetMapping("/suc/user/getbyid")
    SucUserRo getByIdEx(@RequestParam("id") java.lang.Long id) {
        SucUserMo result = svc.getById(id);
        _log.info("get: " + result);
        SucUserRo ro = new SucUserRo();
        if (result == null) {
            String msg = "获取失败，没有找到该条记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        } else {
            String msg = "获取成功";
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
    SucUserRo modify(@RequestBody SucUserMo mo) {
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
    SucUserRo setLoginPw(@RequestBody SucUserMo mo) {
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
    SucUserRo enable(@RequestBody SucUserMo mo) {
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
    SucUserRo removeLoginPassWord(@RequestParam("id") Long id) {
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
    SucUserRo removePayPassWord(@RequestParam("id") Long id) {
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
    SucUserRo unbindWeChat(@RequestParam("id") Long id) {
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
    SucUserRo unbindQQ(@RequestParam("id") Long id) {
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
    @GetMapping("/suc/user/listuserbyids")
    PageInfo<SucUserMo> listUserByIds(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("ids") String ids) {
        _log.info("查询用户信息的参数为：pageNum={}, pageSize={}, ids={}", pageNum, pageSize, ids);
        return svc.listUserByIds(pageNum, pageSize, ids);
    }

    /**
     * 根据组织查询用户信息
     */
    @GetMapping("/suc/user/listbyorgid")
    PageInfo<SucUserMo> listByOrgId(@RequestParam("orgId") Long orgId, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        _log.info("查询用户信息的参数为：{}, pageNum={}, pageSize={}", orgId, pageNum, pageSize);
        SucUserMo mo = new SucUserMo();
        mo.setOrgId(orgId);
        return svc.list(mo, pageNum, pageSize);
    }

    /**
     * 添加用户组织
     */
    @PutMapping("/suc/user/adduserorg")
    SucUserRo addUserOrg(@RequestParam("id") Long id, @RequestParam("orgId") Long orgId) {
        _log.info("添加用户组织的请求参数为：{}, {}", id, orgId);
        return svc.addUserOrg(id, orgId);
    }

    /**
     * 删除用户组织
     */
    @PutMapping("/suc/user/deluserorgbyid")
    SucUserRo delUserOrgById(@RequestParam("id") Long id) {
        _log.info("删除用户组织的参数为：{}", id);
        return svc.delUserOrgById(id);
    }

    /**
     * 获取当前用户信息
     * 
     * @throws ParseException
     */
    @GetMapping("/user/currentuser")
    CurrentUserRo getCurrentUser(HttpServletRequest req) throws ParseException {
        _log.info("获取当前用户信息");
        // 从签名中获取用户ID
        Long userId = JwtUtils.getJwtUserIdInCookie(req);
        // 通过用户ID获取用户信息
        return svc.getCurrentUser(userId);
    }

    /**
     * 获取用户购买商品的购买关系
     * 
     * @throws ParseException
     */
    @GetMapping("/user/getBuyRelation")
    Long getBuyRelation(@RequestParam("userId") Long userId, @RequestParam("onlineId") Long onlineId) throws ParseException {
        _log.info("获取用户购买商品的购买关系");
        return svc.getBuyRelation(userId, onlineId);
    }

}
