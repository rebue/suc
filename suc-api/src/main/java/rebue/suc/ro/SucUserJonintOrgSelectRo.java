package rebue.suc.ro;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
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
	 * 是否是测试号
	 */
	private Boolean isTester;

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



}
