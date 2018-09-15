package rebue.suc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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

}
