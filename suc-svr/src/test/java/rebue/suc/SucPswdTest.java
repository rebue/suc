package rebue.suc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import rebue.wheel.OkhttpUtils;

/**  
* 创建时间：2018年4月28日 下午2:16:23  
* 项目名称：suc-svr  
* @author daniel  
* @version 1.0   
* @since JDK 1.8  
* 文件名称：SucPswdTest.java  
* 类说明：  
*/
public class SucPswdTest {

	private String host = "http://localhost:9100";
	
	@Test
	public void setOrUpdatePassword() throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wxId", "ov3CM0uSM_Y47VM3J8tZomvdnhlY");
		map.put("newLoginPswd", "ed705fcb9814f5e5910c8c4ec5df66d7");
		/*map.put("oldLoginPswd", "ed705fcb9814f5e5910c8c4ec5df66d7");*/
		String url = host + "/loginpswd/setorupdate";
		String result = OkhttpUtils.putByFormParams(url, map);
		System.out.println(result);
	}
}
  

