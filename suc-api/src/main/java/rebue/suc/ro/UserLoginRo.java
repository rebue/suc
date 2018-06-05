package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.suc.dic.LoginResultDic;

/**
 * 登录的返回结果
 * 
 * @see LoginResultDic
 */
@ApiModel(value = "登录结果", description = "登录的返回结果")
@JsonInclude(Include.NON_NULL)
public class UserLoginRo {
    @ApiModelProperty(value = "用户ID", example = "1")
    private Long           userId;
    @ApiModelProperty(value = "登录返回结果代码", example = "1", required = true)
    private LoginResultDic result;
    @ApiModelProperty(value = "用户昵称")
    private String         nickname;
    @ApiModelProperty(value = "用户头像")
    private String         face;
    @ApiModelProperty(value = "签名(成功后可将签名放入Cookie中)")
    private String         sign;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LoginResultDic getResult() {
        return result;
    }

    public void setResult(LoginResultDic result) {
        this.result = result;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "UserLoginRo [userId=" + userId + ", result=" + result + ", nickname=" + nickname + ", face=" + face + ", sign=" + sign + "]";
    }

}
