package rebue.suc.msg;

import lombok.Data;

@Data
public class SucAddOrgDoneMsg {
    /**
     * 用户ID
     */
    private Long id;
    
    /**
     * 账户类型(1：普通用户  2：组织用户)
     */
    private Byte accountType;

}
