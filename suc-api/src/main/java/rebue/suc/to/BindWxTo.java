package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户绑定微信的传输对象（参数）
 */
@ApiModel(value = "用户绑定微信的传输对象（参数）")
@JsonInclude(Include.NON_NULL)
public class BindWxTo extends RegAndLoginBaseTo {
    @ApiModelProperty(value = "用户ID", required = true)
    private Long   userId;
    @ApiModelProperty(value = "微信的ID", required = true)
    private String wxId;
    @ApiModelProperty(value = "微信昵称", required = true)
    private String wxNickname;
    @ApiModelProperty(value = "微信头像", required = true)
    private String wxFace;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
        return "BindWxTo [userId=" + userId + ", wxId=" + wxId + ", wxNickname=" + wxNickname + ", wxFace=" + wxFace + ", getSysId()=" + getSysId() + ", getUserAgent()="
                + getUserAgent() + ", getMac()=" + getMac() + ", getIp()=" + getIp() + "]";
    }

}
