package rebue.suc.ro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.suc.dic.RegResultDic;

/**
 * 注册的返回结果
 * 
 * @see RegResultDic
 */
@ApiModel(value = "注册结果", description = "用户注册的返回结果")
@JsonInclude(Include.NON_NULL)
public class UserRegRo {

    @ApiModelProperty(value = "用户ID", example = "1")
    private Long         userId;
    @ApiModelProperty(value = "注册返回结果代码", example = "1", required = true)
    private RegResultDic result;
    /**
     * 返回的结果
     */
    private String       msg;
    @ApiModelProperty(value = "签名(成功后可将签名放入Cookie中)")
    private String       sign;
    @ApiModelProperty(value = "签名过期时间(成功后可将签名过期时间放入Cookie中)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date         expirationTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RegResultDic getResult() {
        return result;
    }

    public void setResult(RegResultDic result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        return "UserRegRo [userId=" + userId + ", result=" + result + ", msg=" + msg + ", sign=" + sign + ", expirationTime=" + expirationTime + "]";
    }

}
