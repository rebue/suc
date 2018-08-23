package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 用户信息
 *
 * 数据库表: SUC_USER
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SucUserMo implements Serializable {

    /**
     *    用户ID
     *
     *    数据库字段: SUC_USER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_USER.ORG_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long orgId;

    /**
     *    登录账号
     *
     *    数据库字段: SUC_USER.LOGIN_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String loginName;

    /**
     *    登录密码
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.LOGIN_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String loginPswd;

    /**
     *    支付密码
     *                用户的支付密码默认和登录密码一致
     *                保存在字段的计算方法如下：
     *                MD5(数据库存储的已加密的登陆密码)
     *
     *    数据库字段: SUC_USER.PAY_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String payPswd;

    /**
     *    密码组合码
     *                与密码组合加密用
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.SALT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String salt;

    /**
     *    昵称
     *
     *    数据库字段: SUC_USER.NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String nickname;

    /**
     *    头像
     *
     *    数据库字段: SUC_USER.FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String face;

    /**
     *    实名
     *
     *    数据库字段: SUC_USER.REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String realname;

    /**
     *    是否已验证实名
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedRealname;

    /**
     *    身份证号
     *
     *    数据库字段: SUC_USER.IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String idcard;

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedIdcard;

    /**
     *    电子邮箱
     *
     *    数据库字段: SUC_USER.EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String email;

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedEmail;

    /**
     *    手机
     *
     *    数据库字段: SUC_USER.MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String mobile;

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isVerifiedMobile;

    /**
     *    QQ的ID
     *
     *    数据库字段: SUC_USER.QQ_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String qqId;

    /**
     *    QQ的openid
     *
     *    数据库字段: SUC_USER.QQ_OPENID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String qqOpenid;

    /**
     *    QQ昵称
     *
     *    数据库字段: SUC_USER.QQ_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String qqNickname;

    /**
     *    QQ头像
     *
     *    数据库字段: SUC_USER.QQ_FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String qqFace;

    /**
     *    微信的ID
     *
     *    数据库字段: SUC_USER.WX_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String wxId;

    /**
     *    微信openid
     *
     *    数据库字段: SUC_USER.WX_OPENID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String wxOpenid;

    /**
     *    微信昵称
     *
     *    数据库字段: SUC_USER.WX_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String wxNickname;

    /**
     *    微信头像
     *
     *    数据库字段: SUC_USER.WX_FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String wxFace;

    /**
     *    是否锁定
     *
     *    数据库字段: SUC_USER.IS_LOCK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isLock;

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_USER.PROMOTER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long promoterId;

    /**
     *    修改时间戳
     *
     *    数据库字段: SUC_USER.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long modifiedTimestamp;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    用户ID
     *
     *    数据库字段: SUC_USER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    用户ID
     *
     *    数据库字段: SUC_USER.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_USER.ORG_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_USER.ORG_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     *    登录账号
     *
     *    数据库字段: SUC_USER.LOGIN_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     *    登录账号
     *
     *    数据库字段: SUC_USER.LOGIN_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     *    登录密码
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.LOGIN_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLoginPswd() {
        return loginPswd;
    }

    /**
     *    登录密码
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.LOGIN_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLoginPswd(String loginPswd) {
        this.loginPswd = loginPswd;
    }

    /**
     *    支付密码
     *                用户的支付密码默认和登录密码一致
     *                保存在字段的计算方法如下：
     *                MD5(数据库存储的已加密的登陆密码)
     *
     *    数据库字段: SUC_USER.PAY_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPayPswd() {
        return payPswd;
    }

    /**
     *    支付密码
     *                用户的支付密码默认和登录密码一致
     *                保存在字段的计算方法如下：
     *                MD5(数据库存储的已加密的登陆密码)
     *
     *    数据库字段: SUC_USER.PAY_PSWD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPayPswd(String payPswd) {
        this.payPswd = payPswd;
    }

    /**
     *    密码组合码
     *                与密码组合加密用
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.SALT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getSalt() {
        return salt;
    }

    /**
     *    密码组合码
     *                与密码组合加密用
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.SALT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *    昵称
     *
     *    数据库字段: SUC_USER.NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *    昵称
     *
     *    数据库字段: SUC_USER.NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *    头像
     *
     *    数据库字段: SUC_USER.FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getFace() {
        return face;
    }

    /**
     *    头像
     *
     *    数据库字段: SUC_USER.FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     *    实名
     *
     *    数据库字段: SUC_USER.REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getRealname() {
        return realname;
    }

    /**
     *    实名
     *
     *    数据库字段: SUC_USER.REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     *    是否已验证实名
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedRealname() {
        return isVerifiedRealname;
    }

    /**
     *    是否已验证实名
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_REALNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedRealname(Boolean isVerifiedRealname) {
        this.isVerifiedRealname = isVerifiedRealname;
    }

    /**
     *    身份证号
     *
     *    数据库字段: SUC_USER.IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     *    身份证号
     *
     *    数据库字段: SUC_USER.IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedIdcard() {
        return isVerifiedIdcard;
    }

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_IDCARD
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedIdcard(Boolean isVerifiedIdcard) {
        this.isVerifiedIdcard = isVerifiedIdcard;
    }

    /**
     *    电子邮箱
     *
     *    数据库字段: SUC_USER.EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getEmail() {
        return email;
    }

    /**
     *    电子邮箱
     *
     *    数据库字段: SUC_USER.EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedEmail() {
        return isVerifiedEmail;
    }

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_EMAIL
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
        this.isVerifiedEmail = isVerifiedEmail;
    }

    /**
     *    手机
     *
     *    数据库字段: SUC_USER.MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getMobile() {
        return mobile;
    }

    /**
     *    手机
     *
     *    数据库字段: SUC_USER.MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsVerifiedMobile() {
        return isVerifiedMobile;
    }

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_MOBILE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsVerifiedMobile(Boolean isVerifiedMobile) {
        this.isVerifiedMobile = isVerifiedMobile;
    }

    /**
     *    QQ的ID
     *
     *    数据库字段: SUC_USER.QQ_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqId() {
        return qqId;
    }

    /**
     *    QQ的ID
     *
     *    数据库字段: SUC_USER.QQ_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    /**
     *    QQ的openid
     *
     *    数据库字段: SUC_USER.QQ_OPENID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqOpenid() {
        return qqOpenid;
    }

    /**
     *    QQ的openid
     *
     *    数据库字段: SUC_USER.QQ_OPENID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    /**
     *    QQ昵称
     *
     *    数据库字段: SUC_USER.QQ_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqNickname() {
        return qqNickname;
    }

    /**
     *    QQ昵称
     *
     *    数据库字段: SUC_USER.QQ_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqNickname(String qqNickname) {
        this.qqNickname = qqNickname;
    }

    /**
     *    QQ头像
     *
     *    数据库字段: SUC_USER.QQ_FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getQqFace() {
        return qqFace;
    }

    /**
     *    QQ头像
     *
     *    数据库字段: SUC_USER.QQ_FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setQqFace(String qqFace) {
        this.qqFace = qqFace;
    }

    /**
     *    微信的ID
     *
     *    数据库字段: SUC_USER.WX_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxId() {
        return wxId;
    }

    /**
     *    微信的ID
     *
     *    数据库字段: SUC_USER.WX_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    /**
     *    微信openid
     *
     *    数据库字段: SUC_USER.WX_OPENID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     *    微信openid
     *
     *    数据库字段: SUC_USER.WX_OPENID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    /**
     *    微信昵称
     *
     *    数据库字段: SUC_USER.WX_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     *    微信昵称
     *
     *    数据库字段: SUC_USER.WX_NICKNAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    /**
     *    微信头像
     *
     *    数据库字段: SUC_USER.WX_FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getWxFace() {
        return wxFace;
    }

    /**
     *    微信头像
     *
     *    数据库字段: SUC_USER.WX_FACE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setWxFace(String wxFace) {
        this.wxFace = wxFace;
    }

    /**
     *    是否锁定
     *
     *    数据库字段: SUC_USER.IS_LOCK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsLock() {
        return isLock;
    }

    /**
     *    是否锁定
     *
     *    数据库字段: SUC_USER.IS_LOCK
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_USER.PROMOTER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getPromoterId() {
        return promoterId;
    }

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_USER.PROMOTER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPromoterId(Long promoterId) {
        this.promoterId = promoterId;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: SUC_USER.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    /**
     *    修改时间戳
     *
     *    数据库字段: SUC_USER.MODIFIED_TIMESTAMP
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setModifiedTimestamp(Long modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", loginName=").append(loginName);
        sb.append(", loginPswd=").append(loginPswd);
        sb.append(", payPswd=").append(payPswd);
        sb.append(", salt=").append(salt);
        sb.append(", nickname=").append(nickname);
        sb.append(", face=").append(face);
        sb.append(", realname=").append(realname);
        sb.append(", isVerifiedRealname=").append(isVerifiedRealname);
        sb.append(", idcard=").append(idcard);
        sb.append(", isVerifiedIdcard=").append(isVerifiedIdcard);
        sb.append(", email=").append(email);
        sb.append(", isVerifiedEmail=").append(isVerifiedEmail);
        sb.append(", mobile=").append(mobile);
        sb.append(", isVerifiedMobile=").append(isVerifiedMobile);
        sb.append(", qqId=").append(qqId);
        sb.append(", qqOpenid=").append(qqOpenid);
        sb.append(", qqNickname=").append(qqNickname);
        sb.append(", qqFace=").append(qqFace);
        sb.append(", wxId=").append(wxId);
        sb.append(", wxOpenid=").append(wxOpenid);
        sb.append(", wxNickname=").append(wxNickname);
        sb.append(", wxFace=").append(wxFace);
        sb.append(", isLock=").append(isLock);
        sb.append(", promoterId=").append(promoterId);
        sb.append(", modifiedTimestamp=").append(modifiedTimestamp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SucUserMo other = (SucUserMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
