package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "登录(登录名称)", description = "用户通过登录名称登录的参数")
@JsonInclude(Include.NON_NULL)
public class LoginByLoginNameTo extends RegAndLoginBaseTo {
    @ApiModelProperty(value = "登录名称")
    private String loginName;
    @ApiModelProperty(value = "登录密码（须将明码经过MD5处理才能作为参数）")
    private String loginPswd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPswd() {
        return loginPswd;
    }

    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd;
    }

    @Override
    public String toString() {
        return "LoginByLoginNameTo [loginName=" + loginName + ", loginPswd=" + loginPswd + ", getSysId()=" + getSysId() + ", getUserAgent()=" + getUserAgent() + ", getMac()="
                + getMac() + ", getIp()=" + getIp() + "]";
    }

}
