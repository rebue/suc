package rebue.suc.ro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.suc.dic.LoginResultDic;

/**
 * 登录的返回结果
 * 
 * @see LoginResultDic
 */
@JsonInclude(Include.NON_NULL)
public class UserLoginRo {
    /**
     * 登录返回结果代码
     */
    private LoginResultDic result;
    /**
     * 登录返回信息
     */
    private String         msg;
    /**
     * 用户ID
     */
    private Long           userId;
    /**
     * 用户组织id
     */
    private Long           orgId;
    /**
     * 用户昵称
     */
    private String         nickname;
    /**
     * 用户头像
     */
    private String         face;
    /**
     * 签名(成功后可将签名放入Cookie中)
     */
    private String         sign;
    /**
     * 签名过期时间(成功后可将签名过期时间放入Cookie中)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date           expirationTime;

    public LoginResultDic getResult() {
        return result;
    }

    public void setResult(LoginResultDic result) {
        this.result = result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "UserLoginRo [result=" + result + ", userId=" + userId + ", msg=" + msg + ", nickname=" + nickname + ", face=" + face + ", orgId=" + orgId + ", sign=" + sign
                + ", expirationTime=" + expirationTime + "]";
    }

}
