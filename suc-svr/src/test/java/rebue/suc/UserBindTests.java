package rebue.suc;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import rebue.suc.dic.BindWxResultDic;
import rebue.suc.ro.BindWxRo;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.RandomEx;

public class UserBindTests {

    private String _hostUrl = "http://localhost:9100";
//    private String       _hostUrl      = "http://120.77.220.106/suc-svr";

    private Long         userId        = 467599399439171585L;

    private ObjectMapper _objectMapper = new ObjectMapper();

    @Test
    public void test01() throws IOException {
        String wxId = RandomEx.randomUUID();
        // 用户绑定微信
        String url = _hostUrl + "/user/bind/wx";
        Map<String, Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("userId", "\"" + userId + "\"");
        paramsMap.put("wxId", wxId);
        paramsMap.put("wxNickname", "微信昵称C");
        paramsMap.put("wxFace", "微信头像C");
        paramsMap.put("appId", 11);
        paramsMap.put("userAgent", "用户代理浏览器类型");
        paramsMap.put("mac", "MAC地址3");
        paramsMap.put("ip", "192.168.1.3");
        BindWxRo ro = _objectMapper.readValue(OkhttpUtils.postByFormParams(url, paramsMap), BindWxRo.class);
        Assert.assertNotNull(ro);
        Assert.assertEquals(BindWxResultDic.SUCCESS, ro.getResult());

        // 再次绑定同一微信ID
        paramsMap = new LinkedHashMap<>();
        paramsMap.put("userId", userId);
        paramsMap.put("wxId", wxId);
        paramsMap.put("wxNickname", "微信昵称D");
        paramsMap.put("wxFace", "微信头像D");
        paramsMap.put("appId", 11);
        paramsMap.put("userAgent", "用户代理浏览器类型");
        paramsMap.put("mac", "MAC地址2");
        paramsMap.put("ip", "192.168.1.4");
        ro = _objectMapper.readValue(OkhttpUtils.postByFormParams(url, paramsMap), BindWxRo.class);
        Assert.assertNotNull(ro);
        Assert.assertEquals(BindWxResultDic.WX_ID_EXIST, ro.getResult());
    }

}
