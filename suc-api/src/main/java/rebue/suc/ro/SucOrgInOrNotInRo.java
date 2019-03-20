package rebue.suc.ro;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import rebue.suc.mo.SucOrgMo;


/**
 * 组织是否已经存在返回值
 * @author jjl
 *
 */

@Data
@JsonInclude(Include.NON_NULL)
public class SucOrgInOrNotInRo {
	
    /**
     * 已添加的组织列表
     */
    private PageInfo<SucOrgMo> addedOrgs;
    /**
     * 未添加的组织列表
     */
    private PageInfo<SucOrgMo> unaddedOrgs;

}
