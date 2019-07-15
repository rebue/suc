package rebue.suc;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import rebue.suc.mo.SucUserMo;
import rebue.suc.to.LoginByUserNameTo;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.idworker.IdWorker3;
import rebue.wheel.turing.DigestUtils;

public class SucLoginTest {
    private String              hostUrl       = "http://localhost:9100";
    private ObjectMapper        _objectMapper = new ObjectMapper();
    private final static Logger _log          = LoggerFactory.getLogger(SucLoginTest.class);

    // @Test
    public void LoginByLoginName() throws IOException {
        LoginByUserNameTo to = new LoginByUserNameTo();
        to.setUserName("admin");
        to.setLoginPswd(DigestUtils.md5AsHexStr("12345678".getBytes()));
        to.setSysId("damai-pos-food-app");
        to.setDomainId("platform");
        String result = OkhttpUtils.postByJsonParams(hostUrl + "/user/login/by/user/name", to);
        System.out.println(result);
    }

    @Test
    public void addBussines() throws IOException {
        SucUserMo[] result = _objectMapper.readValue(
                OkhttpUtils.get(hostUrl + "/suc/user/selectByDomainId" + "?domainId=bussines"), SucUserMo[].class);
        IdWorker3 _idWorker = new IdWorker3();
        for (SucUserMo buyer : result) {
            SucUserMo bussines = new SucUserMo();
            bussines.setId(_idWorker.getId());
            // 组织id
            bussines.setOrgId(buyer.getOrgId());
            buyer.setOrgId(null);
            // 登录账号
            bussines.setLoginName(buyer.getLoginName());
            buyer.setLoginName(null);
            // 登录密码
            bussines.setLoginPswd(buyer.getLoginPswd());
            buyer.setLoginPswd(null);
            // 支付密码
            bussines.setPayPswd(buyer.getPayPswd());
            buyer.setPayPswd(null);
            // 密码组合
            bussines.setSalt(buyer.getSalt());
            buyer.setSalt(null);
            // 领域id
            bussines.setDomainId("bussines");
            buyer.setDomainId("buyer");

            _log.info("开始修改买家信息");
            _log.info("买家账号信息为：buyer-{}", buyer);
            String updateBuyerResult = OkhttpUtils.putByJsonParams(hostUrl + "/suc/user", buyer);
            _log.info("修改买家账号的返回值为：updateBuyerResult-{}", updateBuyerResult);
            _log.info("结束修改买家信息");

            _log.info("开始添加商家信息");
            _log.info("商家账号信息参数为：bussines-{}", bussines);
            String installBussinesResult = OkhttpUtils.putByJsonParams(hostUrl + "/suc/user/installByBuyer", bussines);
            _log.info("添加商家账号信息返回值为：bussines-{}", installBussinesResult);

            _log.info("开始修改商家角色,修改平台的话需要注释掉");
            String pfmHostUrl = "http://localhost:20182";
            String roleResult = OkhttpUtils.put(pfmHostUrl + "/pfm/userrole/updateByUserId?oldUserId=" + buyer.getId()
                    + "&userId=" + bussines.getId());
            _log.info("结束修改商家角色");
            _log.info("结束添加商家信息");
        }
    }

}
