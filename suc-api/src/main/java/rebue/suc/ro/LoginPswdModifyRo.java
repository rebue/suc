package rebue.suc.ro;
/**  
* 创建时间：2018年5月14日 下午2:16:17  
* 项目名称：suc-api  
* @author daniel  
* @version 1.0   
* @since JDK 1.8  
* 文件名称：LoginPswdSetRo.java  
* 类说明：  修改密码返回值
*/

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.suc.dic.LoginPswdModifyDic;

@JsonInclude(Include.NON_NULL)
public class LoginPswdModifyRo {

	/** 返回值 **/
	private LoginPswdModifyDic result;

	/** 返回结果 **/
	private String msg;

	public LoginPswdModifyDic getResult() {
		return result;
	}

	public void setResult(LoginPswdModifyDic result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "LoginPswdSetRo [result=" + result + ", msg=" + msg + "]";
	}

}
