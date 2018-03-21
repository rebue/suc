package rebue.suc.msg;

import io.swagger.annotations.ApiModelProperty;

public class SucUserAddMsg {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SucUserAddMsg [id=" + id + "]";
    }

}
