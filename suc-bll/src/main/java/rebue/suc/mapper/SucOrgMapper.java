package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucOrgMo;

@Mapper
public interface SucOrgMapper extends MybatisBaseMapper<SucOrgMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(SucOrgMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(SucOrgMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    SucOrgMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(SucOrgMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(SucOrgMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SucOrgMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SucOrgMo> selectSelective(SucOrgMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(SucOrgMo record);

    /**
     * 根据组织名称查询组织信息
     * @param name
     * @return
     */
    List<SucOrgMo> selectByName(String name);

    /**
     * 禁用或者启用组织
     * @param id
     * @param isEnabled
     * @return
     */
    @Update("UPDATE SUC_ORG SET IS_ENABLED=#{isEnabled,jdbcType=TINYINT} WHERE ID = #{id,jdbcType=BIGINT}")
    int enable(@Param("id") Long id, @Param("isEnabled") boolean isEnabled);
}
