package rebue.suc.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 在组织中移动用户的传输对象
 */
@JsonInclude(Include.NON_NULL)
@Data
public class OrgMoveUsersTo {
    /**
     * 组织ID
     */
    private Long       id;

    /**
     * 已添加用户列表的模糊查询的关键字
     */
    private String     addedKeys;

    /**
     * 未添加用户列表的模糊查询的关键字
     */
    private String     unaddedKeys;

    /**
     * 已添加用户列表的页码
     */
    private Integer    addedPageNum;

    /**
     * 未添加用户列表的页码
     */
    private Integer    unaddedPageNum;

    /**
     * 移动用户的ID列表
     */
    private List<Long> moveIds;
}
