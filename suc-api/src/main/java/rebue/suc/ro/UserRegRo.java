package rebue.suc.ro;

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
    @ApiModelProperty(value = "签名(成功后可将签名放入Cookie中)")
    private String       sign;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "UserRegRo [userId=" + userId + ", result=" + result + ", sign=" + sign + "]";
    }

}
