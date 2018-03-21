package rebue.suc.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "登录(用户名称)", description = "用户通过用户名称(Email/Moblie/LoginName)登录的参数")
public class LoginByUserNameTo extends RegAndLoginBaseTo {
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
        return "LoginByUserNameTo [userName=" + userName + ", loginPswd=" + loginPswd + ", getAppId()=" + getAppId()
                + ", getUserAgent()=" + getUserAgent() + ", getMac()=" + getMac() + ", getIp()=" + getIp() + "]";
    }

}
