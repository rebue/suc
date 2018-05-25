package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.GetLoginNameRo;
import rebue.suc.ro.SetLoginNameRo;
import rebue.suc.svc.SucUserSvc;

@Api(tags = "用户")
@RestController
public class SucUserCtrl {
    private final static Logger _log = LoggerFactory.getLogger(SucUserCtrl.class);

    @Resource
    private SucUserSvc          svc;

    /**
     * 获取用户信息（通过用户ID）
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
}
