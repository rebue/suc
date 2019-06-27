package rebue.suc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import rebue.suc.to.LoginByUserNameTo;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.turing.DigestUtils;

public class SucLoginTest {
	private String hostUrl = "http://localhost:9100";
	
	@Test
	public void LoginByLoginName() throws IOException {
		LoginByUserNameTo to = new LoginByUserNameTo();
		to.setUserName("admin");
		to.setLoginPswd(DigestUtils.md5AsHexStr("12345678".getBytes()));
		to.setSysId("damai-pos-food-app");
		List<String> list = new ArrayList<String>();
		list.add("platform");
		to.setDomainId(list);
		String result = OkhttpUtils.postByJsonParams(hostUrl + "/user/login/by/user/name", to);
		System.out.println(result);
	}
}
