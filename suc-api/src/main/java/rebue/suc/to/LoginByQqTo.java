package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户通过QQ登录的参数
 * 
 * @author zbz
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class LoginByQqTo extends RegAndLoginBaseTo {
    /**
     * QQ的UnionID
     */
    private String qqId;
    /**
     * QQ的Openid
     */
    private String qqOpenid;
    /**
     * QQ昵称
     */
    private String qqNickname;
    /**
     * QQ头像
     */
    private String qqFace;

}
