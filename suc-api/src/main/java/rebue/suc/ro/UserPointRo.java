package rebue.suc.ro;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserPointRo {
	
	/**
	 *   总积分
	 */
	private  BigDecimal point;
	/**
     *    用户ID
     *
     *    数据库字段: SUC_USER.ID
     *

     */
    private Long id;

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_USER.ORG_ID
     *

     */
    private Long orgId;

    /**
     *    登录账号
     *
     *    数据库字段: SUC_USER.LOGIN_NAME
     *

     */
    private String loginName;

    /**
     *    登录密码
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.LOGIN_PSWD
     *

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

     */
    private String payPswd;

    /**
     *    密码组合码
     *                与密码组合加密用
     *                登录密码=小写(MD5(小写(MD5(密码明文))+小写(密码组合码)))
     *
     *    数据库字段: SUC_USER.SALT
     *

     */
    private String salt;

    /**
     *    昵称
     *
     *    数据库字段: SUC_USER.NICKNAME
     *

     */
    private String nickname;

    /**
     *    头像
     *
     *    数据库字段: SUC_USER.FACE
     *

     */
    private String face;

    /**
     *    实名
     *
     *    数据库字段: SUC_USER.REALNAME
     *

     */
    private String realname;

    /**
     *    是否已验证实名
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_REALNAME
     *

     */
    private Boolean isVerifiedRealname;

    /**
     *    身份证号
     *
     *    数据库字段: SUC_USER.IDCARD
     *

     */
    private String idcard;

    /**
     *    是否已验证身份证号
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_IDCARD
     *

     */
    private Boolean isVerifiedIdcard;

    /**
     *    电子邮箱
     *
     *    数据库字段: SUC_USER.EMAIL
     *

     */
    private String email;

    /**
     *    是否已验证电子邮箱
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_EMAIL
     *

     */
    private Boolean isVerifiedEmail;

    /**
     *    手机
     *
     *    数据库字段: SUC_USER.MOBILE
     *

     */
    private String mobile;

    /**
     *    是否已验证手机号码
     *
     *    数据库字段: SUC_USER.IS_VERIFIED_MOBILE
     *

     */
    private Boolean isVerifiedMobile;

    /**
     *    QQ的ID
     *
     *    数据库字段: SUC_USER.QQ_ID
     *

     */
    private String qqId;

    /**
     *    QQ的openid
     *
     *    数据库字段: SUC_USER.QQ_OPENID
     *

     */
    private String qqOpenid;

    /**
     *    QQ昵称
     *
     *    数据库字段: SUC_USER.QQ_NICKNAME
     *

     */
    private String qqNickname;

    /**
     *    QQ头像
     *
     *    数据库字段: SUC_USER.QQ_FACE
     *

     */
    private String qqFace;

    /**
     *    微信的ID
     *
     *    数据库字段: SUC_USER.WX_ID
     *

     */
    private String wxId;

    /**
     *    微信openid
     *
     *    数据库字段: SUC_USER.WX_OPENID
     *

     */
    private String wxOpenid;

    /**
     *    微信昵称
     *
     *    数据库字段: SUC_USER.WX_NICKNAME
     *

     */
    private String wxNickname;

    /**
     *    微信头像
     *
     *    数据库字段: SUC_USER.WX_FACE
     *

     */
    private String wxFace;

    /**
     *    是否锁定
     *
     *    数据库字段: SUC_USER.IS_LOCK
     *

     */
    private Boolean isLock;

    /**
     *    推广者ID
     *
     *    数据库字段: SUC_USER.PROMOTER_ID
     *

     */
    private Long promoterId;

    /**
     *    修改时间戳
     *
     *    数据库字段: SUC_USER.MODIFIED_TIMESTAMP
     *

     */
    private Long modifiedTimestamp;

    /**
     *    是否测试者
     *
     *    数据库字段: SUC_USER.IS_TESTER
     *

     */
    private Boolean isTester;






}
