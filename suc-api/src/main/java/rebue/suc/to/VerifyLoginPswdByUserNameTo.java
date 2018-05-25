package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "通过用户名称校验登录密码的传输对象（参数）")
@JsonInclude(Include.NON_NULL)
public class VerifyLoginPswdByUserNameTo extends RegAndLoginBaseTo {
    @ApiModelProperty(value = "用户名称(Email/Moblie/LoginName)")
    private String userName;
    @ApiModelProperty(value = "登录密码（须将明码经过MD5处理才能作为参数）")
    private String loginPswd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPswd() {
        return loginPswd;
    }

    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd;
    }

    @Override
    public String toString() {
        return "VerifyLoginPswdByUserNameTo [userName=" + userName + ", loginPswd=" + loginPswd + ", getAppId()=" + getAppId() + ", getUserAgent()=" + getUserAgent()
                + ", getMac()=" + getMac() + ", getIp()=" + getIp() + "]";
    }

}
