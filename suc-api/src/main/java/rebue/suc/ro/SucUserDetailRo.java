package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 用户信息(不包含敏感的密码数据)
 *
 * 数据库表: SUC_USER
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class SucUserDetailRo {

    /**
     * 用户ID
     *
     * 数据库字段: SUC_USER.ID
     *
     */
    private Long    id;

    /**
     * 公司/组织id
     *
     * 数据库字段: SUC_USER.ORG_ID
     *
     */
    private Long    orgId;

    /**
     * 登录账号
     *
     * 数据库字段: SUC_USER.LOGIN_NAME
     *
     */
    private String  loginName;

    /**
     * 昵称
     *
     * 数据库字段: SUC_USER.NICKNAME
     *
     */
    private String  nickname;

    /**
     * 头像
     *
     * 数据库字段: SUC_USER.FACE
     *
     */
    private String  face;

    /**
     * 实名
     *
     * 数据库字段: SUC_USER.REALNAME
     *
     */
    private String  realname;

    /**
     * 是否已验证实名
     *
     * 数据库字段: SUC_USER.IS_VERIFIED_REALNAME
     *
     */
    private Boolean isVerifiedRealname;

    /**
     * 身份证号
     *
     * 数据库字段: SUC_USER.IDCARD
     *
     */
    private String  idcard;

    /**
     * 是否已验证身份证号
     *
     * 数据库字段: SUC_USER.IS_VERIFIED_IDCARD
     *
     */
    private Boolean isVerifiedIdcard;

    /**
     * 电子邮箱
     *
     * 数据库字段: SUC_USER.EMAIL
     *
     */
    private String  email;

    /**
     * 是否已验证电子邮箱
     *
     * 数据库字段: SUC_USER.IS_VERIFIED_EMAIL
     *
     */
    private Boolean isVerifiedEmail;

    /**
     * 手机
     *
     * 数据库字段: SUC_USER.MOBILE
     *
     */
    private String  mobile;

    /**
     * 是否已验证手机号码
     *
     * 数据库字段: SUC_USER.IS_VERIFIED_MOBILE
     *
     */
    private Boolean isVerifiedMobile;

    /**
     * QQ的ID
     *
     * 数据库字段: SUC_USER.QQ_ID
     *
     */
    private String  qqId;

    /**
     * QQ的openid
     *
     * 数据库字段: SUC_USER.QQ_OPENID
     *
     */
    private String  qqOpenid;

    /**
     * QQ昵称
     *
     * 数据库字段: SUC_USER.QQ_NICKNAME
     *
     */
    private String  qqNickname;

    /**
     * QQ头像
     *
     * 数据库字段: SUC_USER.QQ_FACE
     *
     */
    private String  qqFace;

    /**
     * 微信的ID
     *
     * 数据库字段: SUC_USER.WX_ID
     *
     */
    private String  wxId;

    /**
     * 微信openid
     *
     * 数据库字段: SUC_USER.WX_OPENID
     *
     */
    private String  wxOpenid;

    /**
     * 微信昵称
     *
     * 数据库字段: SUC_USER.WX_NICKNAME
     *
     */
    private String  wxNickname;

    /**
     * 微信头像
     *
     * 数据库字段: SUC_USER.WX_FACE
     *
     */
    private String  wxFace;

    /**
     * 是否锁定
     *
     * 数据库字段: SUC_USER.IS_LOCK
     *
     */
    private Boolean isLock;

    /**
     * 推广者ID
     *
     * 数据库字段: SUC_USER.PROMOTER_ID
     *
     */
    private Long    promoterId;

    /**
     * 修改时间戳
     *
     * 数据库字段: SUC_USER.MODIFIED_TIMESTAMP
     *
     */
    private Long    modifiedTimestamp;

}
