package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.suc.dic.VerifyLoginPswdResultDic;

/**
 * 登录密码校验的返回结果
 */
@ApiModel(value = "登录密码校验的结果")
@JsonInclude(Include.NON_NULL)
public class LoginPswdVerifyRo {
    @ApiModelProperty(value = "登录密码校验的返回结果代码", example = "1", required = true)
    private VerifyLoginPswdResultDic result;

    public VerifyLoginPswdResultDic getResult() {
        return result;
    }

    public void setResult(VerifyLoginPswdResultDic result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LoginPswdVerifyRo [result=" + result + "]";
    }

}
