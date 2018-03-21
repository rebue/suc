package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rebue.suc.ro.BindWxRo;
import rebue.suc.svc.SucUserSvc;
import rebue.suc.to.BindWxTo;

@Api(tags = "用户绑定")
@RestController
public class BindCtrl {
    private final static Logger _log = LoggerFactory.getLogger(BindCtrl.class);

    @Resource
    private SucUserSvc          svc;

    /**
     * 用户绑定微信
     */
    @PostMapping("/user/bind/wx")
    BindWxRo bindWx(BindWxTo to) {
        _log.info("用户绑定微信: {}", to);
        return svc.bindWx(to);
    }

}
