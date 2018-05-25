package rebue.suc.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import rebue.suc.dic.GetLoginNameDic;

/**
 * 获取用户名称返回类
 */
@JsonInclude(Include.NON_NULL)
public class GetLoginNameRo {

    /**
     * 返回值
     */
    private GetLoginNameDic result;

    /**
     * 返回结果
     */
    private String          msg;

    /**
     * 登录名称
     */
    private String          loginName;

    public GetLoginNameDic getResult() {
        return result;
    }

    public void setResult(GetLoginNameDic result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "GetLoginNameRo [result=" + result + ", msg=" + msg + ", loginName=" + loginName + "]";
    }

}
