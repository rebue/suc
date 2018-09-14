package rebue.suc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import rebue.suc.ro.SucUserDetailRo;

@Mapper
public interface SucUserOrgMapper {

    /**
     * 添加用户到组织中
     * 
     * @param orgId
     *            组织ID
     * @param userIds
     *            用户ID列表串，用逗号分隔开
     */
    @Update("UPDATE SUC_USER SET ORG_ID=#{orgId,jdbcType=BIGINT} WHERE ID IN (${userIds})")
    int addUsers(@Param("orgId") Long orgId, @Param("userIds") String userIds);

    /**
     * 从组织中移除用户
     * 
     * @param userIds
     *            用户ID列表串，用逗号分隔开
     */
    @Update("UPDATE SUC_USER SET ORG_ID=NULL WHERE ID IN (${userIds})")
    int delUsers(@Param("userIds") String userIds);

    /**
     * 查询指定组织的已添加的用户列表
     * 
     * @param orgId
     *            组织ID
     */
    @Select("SELECT * FROM SUC_USER WHERE ORG_ID = #{orgId,jdbcType=BIGINT}")
    List<SucUserDetailRo> selectAddedUsersByOrgId(@Param("orgId") Long orgId);

    /**
     * 查询指定组织的已添加的用户列表
     * 
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     */
    @Select("SELECT * FROM SUC_USER WHERE ORG_ID = #{orgId,jdbcType=BIGINT} AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%')")
    List<SucUserDetailRo> selectAddedUsersByOrgIdAndKeys(@Param("orgId") Long orgId, @Param("keys") String keys);

    /**
     * 查询指定组织的未添加的用户列表
     * 
     * @param orgId
     *            组织ID
     */
    @Select("SELECT * FROM SUC_USER WHERE ORG_ID IS NULL || ORG_ID != #{orgId,jdbcType=BIGINT}")
    List<SucUserDetailRo> selectUnaddedUsersByOrgId(@Param("orgId") Long orgId);

    /**
     * 查询指定组织的未添加的用户列表
     * 
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     */
    @Select("SELECT * FROM SUC_USER WHERE (ORG_ID IS NULL || ORG_ID != #{orgId,jdbcType=BIGINT}) AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%')")
    List<SucUserDetailRo> selectUnaddedUsersByOrgIdAndKeys(@Param("orgId") Long orgId, @Param("keys") String keys);

}
