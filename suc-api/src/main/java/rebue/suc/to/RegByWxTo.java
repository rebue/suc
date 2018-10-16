package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 注册用户(通过微信)的传输对象
 */
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class RegByWxTo extends RegBaseTo {
    /**
     * 微信的ID
     */
    private String wxId;
    /**
     * 微信openid
     */
    private String wxOpenid;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 微信头像
     */
    private String wxFace;

    /**
     * 推广人id
     */
    private Long   promoterId;

    /**
     * 上线id
     * TODO 删除此属性
     * 
     * @deprecated
     */
    @Deprecated
    private Long   onlineId;

}
