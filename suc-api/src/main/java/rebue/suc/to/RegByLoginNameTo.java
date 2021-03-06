package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册用户的传输对象
 */
@ApiModel(value = "注册(登录名称)", description = "用户通过登录名称注册的参数")
@JsonInclude(Include.NON_NULL)
public class RegByLoginNameTo extends RegBaseTo {
    @ApiModelProperty(value = "登录名称", required = true)
    private String loginName;
    @ApiModelProperty(value = "登录密码（须将明码经过MD5处理才能作为参数）", required = true)
    private String loginPswd;
    @ApiModelProperty(value = "Email")
    private String email;
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "实名")
    private String realname;
    @ApiModelProperty(value = "身份证号")
    private String idcard;
    @ApiModelProperty(value = "领域id")
    private String domainId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Override
    public String toString() {
        return "RegByLoginNameTo [loginName=" + loginName + ", loginPswd=" + loginPswd + ", email=" + email
                + ", mobile=" + mobile + ", nickname=" + nickname + ", realname=" + realname + ", idcard=" + idcard
                + ", domainId=" + domainId + ", getSysId()=" + getSysId() + ", getUserAgent()=" + getUserAgent()
                + ", getMac()=" + getMac() + ", getIp()=" + getIp() + "]";
    }

}
