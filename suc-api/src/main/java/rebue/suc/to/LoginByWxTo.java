package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录(微信)
 * 用户通过微信登录的参数
 */
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class LoginByWxTo extends RegAndLoginBaseTo {
    /**
     * 用户不存在时先注册(默认不传此参数为false)
     */
    private Boolean regWhenNoExist = false;
    /**
     * 微信的UnionID
     */
    private String  wxId;
    /**
     * 微信openid
     */
    private String  wxOpenid;
    /**
     * 微信昵称
     */
    private String  wxNickname;
    /**
     * 微信头像
     */
    private String  wxFace;

    /**
     * 推广人id
     */
    private Long    promoterId;

    /**
     * 上线id
     * TODO 删除此属性
     * 
     * @deprecated
     */
    @Deprecated
    private Long    onlineId;

}
