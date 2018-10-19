package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.suc.dic.PayPswdSetDic;

/**
 * 设置支付密码返回类
 * @author lbl
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class PayPswdSetRo {

	/** 返回值 **/
	private PayPswdSetDic result;

	/** 返回结果 **/
	private String msg;
}
