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
     * 微信的UnionID
     */
    private String wxId;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 微信头像
     */
    private String wxFace;
    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * 推广人id
     */
    private Long   promoterId;

    /**
     * 上线id
     */
    private Long   onlineId;
    
    /**
     * 领域id
     */
    private String domainId; 
}
