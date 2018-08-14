package rebue.suc.svc;

import java.util.List;

import rebue.robotech.svc.MybatisBaseSvc;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.SucOrgRo;

public interface SucOrgSvc extends MybatisBaseSvc<SucOrgMo, java.lang.Long> {

    /**
     *  删除组织
     *  @param id
     *  @return
     */
    SucOrgRo delEx(Long id);

    /**
     * 根据组织名称查询组织信息
     * @param name
     * @return
     */
	List<SucOrgMo> selectByName(String name);
}
