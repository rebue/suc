package rebue.suc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.OrgAccountRo;

@Mapper
public interface SucOrgMapper extends MybatisBaseMapper<SucOrgMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insert(SucOrgMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insertSelective(SucOrgMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    SucOrgMo selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKeySelective(SucOrgMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKey(SucOrgMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<SucOrgMo> selectAll();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<SucOrgMo> selectSelective(SucOrgMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existSelective(SucOrgMo record);

    /**
     * 模糊查询组织
     *
     * @param keys
     *             模糊查询的关键字
     */
    @Select("SELECT * FROM SUC_ORG WHERE NAME LIKE '%${keys}%' OR REMARK LIKE '%${keys}%' order by CREATE_TIMESTAMP  ")
    List<SucOrgMo> selectByKeys(@Param("keys") String keys);

    /**
     * 根据组织名称查询组织信息
     *
     * @param name
     * @return
     */
    List<SucOrgMo> selectByName(String name);

    /**
     * 禁用或者启用组织
     *
     * @param id
     * @param isEnabled
     * @return
     */
    @Update("UPDATE SUC_ORG SET IS_ENABLED=#{isEnabled,jdbcType=TINYINT} WHERE ID = #{id,jdbcType=BIGINT}")
    int enable(@Param("id") Long id, @Param("isEnabled") boolean isEnabled);

    /**
     * 根据组织名称查询组织信息
     */
    List<OrgAccountRo> selectOrg(SucOrgMo mo);

    /**
     * 根据组织id集合查询指定组织的未添加的组织列表
     * 
     * @param keys
     *             模糊查询的关键字
     */
    @Select("SELECT * FROM SUC_ORG where ID not  in(${orgIds}) ")
    List<SucOrgMo> selectUnaddedOrgsByOrgIds(@Param("orgIds") String orgIds);

    /**
     * 根据组织id集合和关键字查询指定组织的未添加的组织列表
     * 
     * @param keys
     *             模糊查询的关键字
     */
    @Select("SELECT * FROM SUC_ORG where ID not  in(${orgIds}) and name LIKE '%${keys}%' ")
    List<SucOrgMo> selectUnaddedOrgsByOrgIdsAndKeys(@Param("orgIds") String orgIds, @Param("keys") String keys);

    /**
     * 根据组织id集合查询指定组织的已添加的组织列表
     *
     * @param orgId
     *              组织ID集合
     */
    @Select("SELECT * FROM SUC_ORG where ID in(${orgIds})")
    List<SucOrgMo> selectAddedOrgsByOrgIds(@Param("orgIds") String orgIds);

    /**
     * 根据组织id集合和关键字查询指定组织的已添加的组织列表
     *
     * @param orgId
     *              组织ID集合
     */
    @Select("SELECT * FROM SUC_ORG where ID in(${orgIds}) and name LIKE '%${keys}%' ")
    List<SucOrgMo> selectAddedOrgsByOrgIdsAndKeys(@Param("orgIds") String orgIds, @Param("keys") String keys);

    /**
     * 根据组织编号模糊查询组织id
     * 
     * @param orgCode
     * @return
     */
    @Select("SELECT id,org_code FROM suc.SUC_ORG WHERE org_code LIKE '${orgCode}%'")
    List<SucOrgMo> selectIdByOrgCode(@Param("orgCode") String orgCode);

    /**
     * 根据组织名称模糊查询组织id
     * 
     * @param name
     * @return
     */
    @Select("SELECT id,name FROM SUC_ORG WHERE NAME LIKE '%${name}%' OR REMARK LIKE '%${name}%' ")
    List<SucOrgMo> selectIdByName(@Param("name") String name);
}
