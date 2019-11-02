package rebue.suc.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 注册的基础传输对象
 */
@Getter
@Setter
@ToString
public abstract class RegBaseTo extends RegAndLoginBaseTo {
    /**
     * 推广者ID
     */
    private Long    promoterId;

    /**
     * 公司/组织id
     */
    private Long    orgId;

    /**
     * 是否添加到当前操作人的组织
     */
    private Boolean isAddToCurOrg;
}
