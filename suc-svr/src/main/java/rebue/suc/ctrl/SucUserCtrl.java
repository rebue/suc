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
import rebue.suc.mo.SucUserMo;
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
     * 通过微信ID设置登录名称
     * Title: setLoginName
     * Description: 
     * @param wxId
     * @param loginName
     * @return
     * @date 2018年5月3日 下午6:09:55
     */
    @PostMapping("/user/setloginname/bywxid")
    Map<String, Object> setLoginName(@RequestParam("wxId") String wxId, @RequestParam("loginName") String loginName) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	try {
			return svc.setLoginName(wxId, loginName);
		} catch (RuntimeException e) {
			String msg = e.getMessage();
			if (msg.equals("您未登录，请先登录")) {
				resultMap.put("result", -1);
				resultMap.put("msg", msg);
			} else if (msg.equals("请输入登录名称")) {
				resultMap.put("result", -2);
				resultMap.put("msg", msg);
			} else if (msg.equals("您未注册，请先注册")) {
				resultMap.put("result", -3);
				resultMap.put("msg", msg);
			} else if (msg.equals("设置失败")) {
				resultMap.put("result", -4);
				resultMap.put("msg", msg);
			} else if (msg.equals("该名称已存在")) {
				resultMap.put("result", -5);
				resultMap.put("msg", msg);
			} else {
				resultMap.put("result", -6);
				resultMap.put("msg", "设置失败");
			}
			return resultMap;
		}
    }
    
    /**
     * 根据微信ID获取用户登录名称
     * Title: selectLoginNameByWx
     * Description: 
     * @param wxId
     * @return
     * @date 2018年5月4日 上午9:06:34
     */
    @GetMapping("/user/loginName/bywxid")
    String selectLoginNameByWx(@RequestParam("wxId") String wxId) {
    	return svc.selectLoginNameByWx(wxId);
    }
}
