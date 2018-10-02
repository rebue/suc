package rebue.suc.ro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.suc.dic.LoginResultDic;

/**
 * 登录的返回结果
 * 
 * @see LoginResultDic
 */
@Data
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
     * 用户WxOpenID
     */
    private String         userWxOpenId;
    /**
     * 用户WxUnionID
     */
    private String         userWxUnionId;
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

}
