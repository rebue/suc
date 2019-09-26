package rebue.suc.svc;

import java.util.List;

import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.MybatisBaseSvc;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.OrgAccountRo;
import rebue.suc.ro.SucOrgInOrNotInRo;
import rebue.suc.ro.SucOrgRo;

public interface SucOrgSvc extends MybatisBaseSvc<SucOrgMo, java.lang.Long> {

    /**
     * 根据组织名称查询组织信息
     * 
     * @param name
     * @return
     */
    List<SucOrgMo> listByName(String name);

    /**
     * 设置禁用或者启用组织
     * 
     * @param id
     * @param isEnabled
     * @return
     */
    SucOrgRo enable(Long id, Boolean isEnabled);

    /**
     * 分页模糊查询组织
     * 
     * @param keys
     *            模糊查询的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    PageInfo<SucOrgMo> list(String keys, int pageNum, int pageSize);

    /**
     * 分页查询
     * 
     * @param keys
     *            模糊查询的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    PageInfo<OrgAccountRo> listOrgAccount(SucOrgMo mo, int pageNum, int pageSize);

    /**
     * 根据组织id集合查询已经存在和没有存在的组织
     * 
     * @param orgIds
     *            组织ID集合
     * @param pageSize
     *            每页大小
     * @param addedKeys
     *            模糊查询已添加用户的关键字
     * @param addedPageNum
     *            已添加用户是第几页
     * @param unaddedKeys
     *            模糊查询未添加用户的关键字
     * @param unaddedPageNum
     *            未添加用户是第几页
     */
    SucOrgInOrNotInRo listAddedAndUnaddedOrgs(String orgIds, Integer pageSize, String addedKeys, Integer addedPageNum,
            String unaddedKeys, Integer unaddedPageNum);

    /**
     * 查询指定组织的已添加的用户列表
     * 
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    PageInfo<SucOrgMo> listAddedOrgs(String orgIds, String keys, Integer pageNum, Integer pageSize);

    /**
     * 
     * 
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    PageInfo<SucOrgMo> listUnaddedOrgs(String orgIds, String keys, Integer pageNum, Integer pageSize);

    /**
     * 根据组织编号模糊查询组织id
     * 
     * @param orgCode
     * @return
     */
    List<SucOrgMo> selectIdByOrgCode(String orgCode);

    /**
     * 根据组织名称模糊查询组织id
     * 
     * @param name
     * @return
     */
    List<SucOrgMo> selectIdByName(String name);
}
