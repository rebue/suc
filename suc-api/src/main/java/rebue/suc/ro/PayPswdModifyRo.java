package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.suc.dic.PayPswdModifyDic;

/**
 * 修改支付秘密返回类
 * @author lbl
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class PayPswdModifyRo {

	/** 返回值 **/
	private PayPswdModifyDic result;

	/** 返回结果 **/
	private String msg;
}
