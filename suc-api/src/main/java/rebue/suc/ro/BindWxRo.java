package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import rebue.suc.dic.BindWxResultDic;

/**
 * 用户绑定微信的返回结果
 */
@ApiModel(value = "用户绑定微信的返回结果")
@JsonInclude(Include.NON_NULL)
public class BindWxRo {

    @ApiModelProperty(value = "用户绑定微信返回结果代码", example = "1", required = true)
    private BindWxResultDic result;

    public BindWxResultDic getResult() {
        return result;
    }

    public void setResult(BindWxResultDic result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BindWxRo [result=" + result + "]";
    }

}
