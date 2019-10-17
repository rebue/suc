package rebue.suc.ro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.suc.dic.RegResultDic;

/**
 * 用户注册的返回结果
 * 
 * @see RegResultDic
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserRegRo {
    /**
     * 注册返回结果代码
     */
    private RegResultDic result;
    /**
     * 返回的结果
     */
    private String       msg;
    /**
     * 用户ID
     */
    private Long         userId;
    /**
     * 用户WxOpenID
     */
    private String       userWxOpenId;
    /**
     * 用户WxUnionID
     */
    private String       userWxUnionId;
    /**
     * 签名(成功后可将签名放入Cookie中)
     */
    private String       sign;
    /**
     * 签名过期时间(成功后可将签名过期时间放入Cookie中)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date         expirationTime;

}
