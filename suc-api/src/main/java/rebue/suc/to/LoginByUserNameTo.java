package rebue.suc.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录(用户名称)
 * 用户通过用户名称(Email/Moblie/LoginName)登录的参数
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString(callSuper = true)
public class LoginByUserNameTo extends RegAndLoginBaseTo {
    /**
     * 用户名称(Email/Moblie/LoginName)
     */
    private String userName;
    /**
     * 登录密码（须将明码经过MD5处理才能作为参数）
     */
    private String loginPswd;

}
