package rebue.suc;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import rebue.suc.mo.SucUserMo;
import rebue.suc.to.LoginByUserNameTo;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.idworker.IdWorker3;
import rebue.wheel.turing.DigestUtils;

@Slf4j
public class SucLoginTest {
    private final String       hostUrl       = "http://localhost:9100";
    private final ObjectMapper _objectMapper = new ObjectMapper();

    @Test
    public void loginByLoginName() throws IOException {
        final LoginByUserNameTo to = new LoginByUserNameTo();
        to.setUserName("admin");
        to.setLoginPswd(DigestUtils.md5AsHexStr("12345678".getBytes()));
        to.setSysId("damai-pos-food-app");
        to.setDomainId("platform");
        final String result = OkhttpUtils.postByJsonParams(hostUrl + "/user/login/by/user/name", to);
        log.info(result);
    }

//    @Test
    public void addBussines() throws IOException {
        final SucUserMo[] result = _objectMapper.readValue(OkhttpUtils.get(hostUrl + "/suc/user/selectByDomainId" + "?domainId=bussines"), SucUserMo[].class);
        final IdWorker3 _idWorker = new IdWorker3();
        for (final SucUserMo buyer : result) {
            final SucUserMo bussines = new SucUserMo();
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

            log.info("开始修改买家信息");
            log.info("买家账号信息为：buyer-{}", buyer);
            final String updateBuyerResult = OkhttpUtils.putByJsonParams(hostUrl + "/suc/user", buyer);
            log.info("修改买家账号的返回值为：updateBuyerResult-{}", updateBuyerResult);
            log.info("结束修改买家信息");

            log.info("开始添加商家信息");
            log.info("商家账号信息参数为：bussines-{}", bussines);
            final String installBussinesResult = OkhttpUtils.putByJsonParams(hostUrl + "/suc/user/installByBuyer", bussines);
            log.info("添加商家账号信息返回值为：bussines-{}", installBussinesResult);

            log.info("开始修改商家角色,修改平台的话需要注释掉");
            final String pfmHostUrl = "http://localhost:20182";
            final String roleResult = OkhttpUtils.put(pfmHostUrl + "/pfm/userrole/updateByUserId?oldUserId=" + buyer.getId() + "&userId=" + bussines.getId());
            log.info("返回结果: {}", roleResult);
            log.info("结束修改商家角色");
            log.info("结束添加商家信息");
        }
    }

}
