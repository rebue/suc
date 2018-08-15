package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SucUserJonintOrgSelectRo {

	/**
	 * 组织id
	 */
	private Long orgId;

	/**
	 * 组织名称
	 */
	private String orgName;

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 登录账号
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String loginPswd;

	/**
	 * 支付密码
	 */
	private String payPswd;

	/**
	 * 密码组合码
	 */
	private String salt;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 头像
	 */
	private String face;

	/**
	 * 实名
	 */
	private String realname;

	/**
	 * 是否已验证实名
	 */
	private Boolean isVerifiedRealname;

	/**
	 * 身份证号
	 */
	private String idcard;

	/**
	 * 是否已验证身份证号
	 */
	private Boolean isVerifiedIdcard;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 是否已验证电子邮箱
	 */
	private Boolean isVerifiedEmail;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 是否已验证手机号码
	 */
	private Boolean isVerifiedMobile;

	/**
	 * QQ的ID
	 */
	private String qqId;

	/**
	 * QQ昵称
	 */
	private String qqNickname;

	/**
	 * QQ头像
	 */
	private String qqFace;

	/**
	 * 微信的ID
	 */
	private String wxId;

	/**
	 * 微信昵称
	 */
	private String wxNickname;

	/**
	 * 微信头像
	 */
	private String wxFace;

	/**
	 * 是否锁定
	 */
	private Boolean isLock;

	/**
	 * 修改时间戳(添加或更新本条记录时的时间戳)
	 */
	private Long modifiedTimestamp;

	/**
	 * QQopenid
	 */
	private String qqOpenid;

	/**
	 * 微信openid
	 */
	private String wxOpenid;

	/**
	 * 推广者id
	 */
	private Long promoterId;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPayPswd() {
		return payPswd;
	}

	public void setPayPswd(String payPswd) {
		this.payPswd = payPswd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Boolean getIsVerifiedRealname() {
		return isVerifiedRealname;
	}

	public void setIsVerifiedRealname(Boolean isVerifiedRealname) {
		this.isVerifiedRealname = isVerifiedRealname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Boolean getIsVerifiedIdcard() {
		return isVerifiedIdcard;
	}

	public void setIsVerifiedIdcard(Boolean isVerifiedIdcard) {
		this.isVerifiedIdcard = isVerifiedIdcard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsVerifiedEmail() {
		return isVerifiedEmail;
	}

	public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
		this.isVerifiedEmail = isVerifiedEmail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsVerifiedMobile() {
		return isVerifiedMobile;
	}

	public void setIsVerifiedMobile(Boolean isVerifiedMobile) {
		this.isVerifiedMobile = isVerifiedMobile;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getQqNickname() {
		return qqNickname;
	}

	public void setQqNickname(String qqNickname) {
		this.qqNickname = qqNickname;
	}

	public String getQqFace() {
		return qqFace;
	}

	public void setQqFace(String qqFace) {
		this.qqFace = qqFace;
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

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
	}

	public Long getModifiedTimestamp() {
		return modifiedTimestamp;
	}

	public void setModifiedTimestamp(Long modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}

	public String getQqOpenid() {
		return qqOpenid;
	}

	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}

	@Override
	public String toString() {
		return "SucUserJonintOrgSelectRo [orgId=" + orgId + ", orgName=" + orgName + ", id=" + id + ", loginName="
				+ loginName + ", loginPswd=" + loginPswd + ", payPswd=" + payPswd + ", salt=" + salt + ", nickname="
				+ nickname + ", face=" + face + ", realname=" + realname + ", isVerifiedRealname=" + isVerifiedRealname
				+ ", idcard=" + idcard + ", isVerifiedIdcard=" + isVerifiedIdcard + ", email=" + email
				+ ", isVerifiedEmail=" + isVerifiedEmail + ", mobile=" + mobile + ", isVerifiedMobile="
				+ isVerifiedMobile + ", qqId=" + qqId + ", qqNickname=" + qqNickname + ", qqFace=" + qqFace + ", wxId="
				+ wxId + ", wxNickname=" + wxNickname + ", wxFace=" + wxFace + ", isLock=" + isLock
				+ ", modifiedTimestamp=" + modifiedTimestamp + ", qqOpenid=" + qqOpenid + ", wxOpenid=" + wxOpenid
				+ ", promoterId=" + promoterId + "]";
	}

}
