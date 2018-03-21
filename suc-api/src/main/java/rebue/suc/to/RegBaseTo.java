package rebue.suc.to;

import io.swagger.annotations.ApiModelProperty;

/**
 * 注册与登录的基础传输对象
 */
public abstract class RegBaseTo extends RegAndLoginBaseTo {
    @ApiModelProperty(value = "推广者ID", required = true)
    private Long promoterId;

    public Long getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(Long promoterId) {
        this.promoterId = promoterId;
    }

}
