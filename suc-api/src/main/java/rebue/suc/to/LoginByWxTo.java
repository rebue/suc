package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "登录(微信)", description = "用户通过微信登录的参数")
@JsonInclude(Include.NON_NULL)
public class LoginByWxTo extends RegAndLoginBaseTo {
    @ApiModelProperty(value = "微信的ID")
    private String wxId;
    @ApiModelProperty(value = "微信昵称")
    private String wxNickname;
    @ApiModelProperty(value = "微信头像")
    private String wxFace;

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxNickname() {
        return wxNickname;
    }

    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    public String getWxFace() {
        return wxFace;
    }

    public void setWxFace(String wxFace) {
        this.wxFace = wxFace;
    }

    @Override
    public String toString() {
        return "LoginByWxTo [wxId=" + wxId + ", wxNickname=" + wxNickname + ", wxFace=" + wxFace + ", getAppId()=" + getAppId() + ", getUserAgent()=" + getUserAgent()
                + ", getMac()=" + getMac() + ", getIp()=" + getIp() + "]";
    }

}
