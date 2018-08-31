package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.suc.dic.LoginResultDic;

/**
 * 登录的返回结果
 * 
 * @see LoginResultDic
 */
@JsonInclude(Include.NON_NULL)
public class CurrentUserRo {
    /**
     * 用户ID
     */
    private Long   userId;
    /**
     * 用户组织id
     */
    private Long   orgId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String face;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    @Override
    public String toString() {
        return "CurrentUserRo [userId=" + userId + ", orgId=" + orgId + ", nickname=" + nickname + ", face=" + face + "]";
    }

}
