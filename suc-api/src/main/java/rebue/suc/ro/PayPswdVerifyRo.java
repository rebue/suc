package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.suc.dic.VerifyPayPswdResultDic;

/**
 * 支付密码校验的返回结果
 */
@ApiModel(value = "支付密码校验的结果", description = "支付密码校验的返回结果")
@JsonInclude(Include.NON_NULL)
public class PayPswdVerifyRo {
    @ApiModelProperty(value = "支付密码校验的返回结果代码", example = "1", required = true)
    private VerifyPayPswdResultDic result;

    public VerifyPayPswdResultDic getResult() {
        return result;
    }

    public void setResult(VerifyPayPswdResultDic result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PayPswdVerifyRo [result=" + result + "]";
    }

}
