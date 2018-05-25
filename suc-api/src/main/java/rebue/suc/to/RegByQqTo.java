package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册用户(通过QQ)的传输对象
 */
@ApiModel(value = "注册(QQ)", description = "用户通过QQ注册的参数")
@JsonInclude(Include.NON_NULL)
public class RegByQqTo extends RegBaseTo {
    @ApiModelProperty(value = "QQ的ID", required = true)
    private String qqId;
    @ApiModelProperty(value = "QQ昵称", required = true)
    private String qqNickname;
    @ApiModelProperty(value = "QQ头像", required = true)
    private String qqFace;

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public String getQqNickname() {
        return qqNickname;
    }

    public void setQqNickname(String qqNickname) {
        this.qqNickname = qqNickname;
    }

    public String getQqFace() {
        return qqFace;
    }

    public void setQqFace(String qqFace) {
        this.qqFace = qqFace;
    }

    @Override
    public String toString() {
        return "RegByQqTo [qqId=" + qqId + ", qqNickname=" + qqNickname + ", qqFace=" + qqFace + "]";
    }

}
