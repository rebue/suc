package rebue.suc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.SucUserJonintOrgSelectRo;
import rebue.suc.ro.UserPointRo;

@Mapper
public interface SucUserMapper extends MybatisBaseMapper<SucUserMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insert(SucUserMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insertSelective(SucUserMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    SucUserMo selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKeySelective(SucUserMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKey(SucUserMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<SucUserMo> selectAll();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<SucUserMo> selectSelective(SucUserMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existSelective(SucUserMo record);

    /**
     * 模糊查询关键字的用户列表
     *
     * @param keys
     *            模糊查询用户的关键字
     */
    @Select("SELECT * FROM SUC_USER WHERE (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%')")
    List<SucUserDetailRo> selectByKeys(@Param("keys") String keys);

    /**
     * 查询在指定多个用户ID范围内的用户列表
     *
     * @param userIds
     *            多个用户ID组成的字符串，用逗号分隔
     */
    @Select("SELECT * FROM SUC_USER WHERE ID IN (${userIds})")
    List<SucUserDetailRo> selectByUserIds(@Param("userIds") String userIds);

    /**
     * 模糊查询关键字且在指定多个用户ID范围内的用户列表
     *
     * @param keys
     *            模糊查询用户的关键字
     * @param userIds
     *            多个用户ID组成的字符串，用逗号分隔
     */
    @Select("SELECT * FROM SUC_USER WHERE ID IN (${userIds}) AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%')")
    List<SucUserDetailRo> selectByKeysAndUserIds(@Param("keys") String keys, @Param("userIds") String userIds);

    /**
     * 查询除指定多个用户ID外的用户列表
     *
     * @param userIds
     *            多个用户ID组成的字符串，用逗号分隔
     */
    @Select("SELECT * FROM SUC_USER WHERE ID NOT IN (${userIds})")
    List<SucUserDetailRo> selectByNotUserIds(@Param("userIds") String userIds);

    /**
     * 模糊查询关键字且排除指定多个用户ID外的用户列表
     *
     * @param keys
     *            模糊查询用户的关键字
     * @param userIds
     *            多个用户ID组成的字符串，用逗号分隔
     */
    @Select("SELECT * FROM SUC_USER WHERE ID NOT IN (${userIds}) AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%')")
    List<SucUserDetailRo> selectByKeysAndNotUserIds(@Param("keys") String keys, @Param("userIds") String userIds);

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
    @Select("SELECT * FROM SUC_USER WHERE ORG_ID = #{orgId,jdbcType=BIGINT} AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%')")
    List<SucUserDetailRo> selectAddedUsersByOrgIdAndKeys(@Param("orgId") Long orgId, @Param("keys") String keys);

    /**
     * 查询指定组织的未添加的用户列表
     *
     * @param orgId
     *            组织ID
     */
    @Select("SELECT * FROM SUC_USER WHERE ORG_ID IS NULL or ORG_ID != #{orgId,jdbcType=BIGINT}")
    List<SucUserDetailRo> selectUnaddedUsersByOrgId(@Param("orgId") Long orgId);

    /**
     * 根据条件查询指定组织的未添加的用户列表
     *
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     */
    @Select("SELECT * FROM SUC_USER WHERE (ORG_ID IS NULL or ORG_ID != #{orgId,jdbcType=BIGINT}) AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%' )")
    List<SucUserDetailRo> selectUnaddedUsersByOrgIdAndKeys(@Param("orgId") Long orgId, @Param("keys") String keys);

    /**
     * 根据组织id、用户id、关键字查询该组织除去指定用户之外的用户列表
     *
     * @param orgId
     *            组织id
     * @param userIds
     *            需要排除的用户id，多个以逗号隔开
     * @param keys
     *            模糊查询用户的关键字
     * @return
     */
    @Select("SELECT * FROM SUC_USER WHERE ID NOT IN (${userIds}) and ORG_ID = #{orgId,jdbcType=BIGINT} AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%' )")
    List<SucUserDetailRo> selectUnaddedUsersByOrgIdAndUserIdsAndKeys(@Param("orgId") Long orgId, @Param("userIds") String userIds, @Param("keys") String keys);

    /**
     * 根据组织id、关键字查询该组织用户列表
     *
     * @param orgId
     *            组织id
     * @param keys
     *            模糊查询用户的关键字
     * @return
     */
    @Select("SELECT * FROM SUC_USER WHERE ORG_ID = #{orgId,jdbcType=BIGINT} AND (LOGIN_NAME LIKE '%${keys}%' OR NICKNAME LIKE '%${keys}%' OR REALNAME LIKE '%${keys}%' OR IDCARD = '%${keys}%' OR EMAIL LIKE '%${keys}%' OR MOBILE LIKE '%${keys}%' OR QQ_NICKNAME LIKE '%${keys}%' OR WX_NICKNAME LIKE '%${keys}%' OR ID LIKE '%${keys}%' )")
    List<SucUserDetailRo> selectUnaddedUsersByOrgIdAndkeys(@Param("orgId") Long orgId, @Param("keys") String keys);

    /**
     * 锁定用户
     */
    @Update("update SUC_USER set IS_LOCK=1 where ID=#{id}")
    int lock(Long id);

    /**
     * 解锁用户
     */
    @Update("update SUC_USER set IS_LOCK=0 where ID=#{id}")
    int unlock(Long id);

    /**
     * 根据ID查询登录密码、密码组合码、微信ID Title: selectUserInfoByWx Description:
     *
     * @param id
     * @return
     * @date 2018年4月28日 上午11:47:26
     */
    @Select("SELECT LOGIN_PSWD, PAY_PSWD, SALT, WX_ID FROM SUC_USER WHERE ID=#{id,jdbcType=BIGINT}")
    SucUserMo selectUserInfoByWx(@Param("id") Long id);

    /**
     * 根据微信ID修改登录密码 Title: updateloginPswd Description:
     *
     * @param id
     * @param loginPswd
     * @return
     * @date 2018年4月28日 上午11:57:49
     */
    @Update("UPDATE SUC_USER SET LOGIN_PSWD = #{loginPswd,jdbcType=VARCHAR} WHERE ID=#{id,jdbcType=BIGINT}")
    int updateloginPswd(@Param("id") Long id, @Param("loginPswd") String loginPswd);

    /**
     * 根据id设置支付密码 Title: setLoginPswd Description:
     *
     * @param id
     * @param loginPswd
     * @param salt
     * @return
     * @date 2018年4月28日 上午11:59:04
     */
    @Update("UPDATE SUC_USER SET PAY_PSWD = #{payPswd,jdbcType=VARCHAR}, SALT = #{salt,jdbcType=VARCHAR} WHERE ID=#{id,jdbcType=BIGINT}")
    int setPayPswd(@Param("id") Long id, @Param("payPswd") String loginPswd, @Param("salt") String salt);

    /**
     * 根据id修改支付密码 Title: updateloginPswd Description:
     *
     * @param id
     * @param loginPswd
     * @return
     * @date 2018年4月28日 上午11:57:49
     */
    @Update("UPDATE SUC_USER SET PAY_PSWD = #{payPswd,jdbcType=VARCHAR} WHERE ID=#{id,jdbcType=BIGINT}")
    int updatePayPswd(@Param("id") Long id, @Param("payPswd") String payPswd);

    /**
     * 根据id设置登录密码 Title: setLoginPswd Description:
     *
     * @param id
     * @param loginPswd
     * @param salt
     * @return
     * @date 2018年4月28日 上午11:59:04
     */
    @Update("UPDATE SUC_USER SET LOGIN_PSWD = #{loginPswd,jdbcType=VARCHAR}, SALT = #{salt,jdbcType=VARCHAR} WHERE ID=#{id,jdbcType=BIGINT}")
    int setLoginPswd(@Param("id") Long id, @Param("loginPswd") String loginPswd, @Param("salt") String salt);

    /**
     * 根据id设置登录名称 Title: setLoginName Description:
     *
     * @param id
     * @param loginName
     * @return
     * @date 2018年5月3日 下午5:30:31
     */
    @Update("UPDATE SUC_USER SET LOGIN_NAME = #{loginName,jdbcType=VARCHAR} WHERE ID=#{id,jdbcType=BIGINT}")
    int setLoginName(@Param("id") Long id, @Param("loginName") String loginName);

    /**
     * 根据微信ID获取用户登录名称 Title: selectLoginNameByWx Description:
     *
     * @param wxId
     * @return
     * @date 2018年5月4日 上午9:02:28
     */
    @Select("SELECT LOGIN_NAME FROM SUC_USER WHERE ID=#{id,jdbcType=BIGINT}")
    String selectLoginNameByWx(@Param("id") Long id);

    /**
     * 设置用户锁定/解锁
     *
     * @param id
     * @param isLock
     * @return
     */
    @Update("update SUC_USER set IS_LOCK=#{isLock,jdbcType=TINYINT} where ID=#{id,jdbcType=BIGINT}")
    int lockOrUnlockById(@Param("id") Long id, @Param("isLock") Boolean isLock);

    /**
     * 根据id解除登录密码
     *
     * @param id
     * @return
     */
    @Update("UPDATE SUC_USER SET LOGIN_PSWD=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int removeLoginPassWord(@Param("id") Long id);

    /**
     * 根据用户id解除支付密码
     *
     * @param id
     * @return
     */
    @Update("UPDATE SUC_USER SET PAY_PSWD=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int removePayPassWord(@Param("id") Long id);

    /**
     * 根据用户id解绑微信
     *
     * @param id
     * @return
     */
    @Update("UPDATE SUC_USER SET WX_ID=NULL, WX_NICKNAME=NULL, WX_FACE=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int unbindWeChat(@Param("id") Long id);

    /**
     * 根据用户id解绑微信
     *
     * @param id
     * @return
     */
    @Update("UPDATE SUC_USER SET QQ_ID=NULL, QQ_NICKNAME=NULL, QQ_FACE=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int unbindQQ(@Param("id") Long id);

    /**
     * 多条件同时查询
     *
     * @param users
     * @return
     */
    List<SucUserMo> selectMeanwhile(@Param("users") String users);

    /**
     * 根据用户id批量查询用户名称
     *
     * @param ids
     * @return
     */
    List<SucUserMo> listUserByIdsAndKeys(@Param("ids") String ids, @Param("keys") String keys);

    /**
     * 根据组织编号删除用户组织
     *
     * @param orgId
     * @return
     */
    @Update("update SUC_USER set ORG_ID=NULL where ORG_ID=#{orgId,jdbcType=BIGINT}")
    int delUserOrgByOrgId(@Param("orgId") Long orgId);

    /**
     * 添加用户组织
     *
     * @param id
     * @param orgId
     * @return
     */
    @Update("update SUC_USER set ORG_ID=#{orgId,jdbcType=BIGINT} where ID=#{id,jdbcType=BIGINT}")
    int insertOrgById(@Param("id") Long id, @Param("orgId") Long orgId);

    /**
     * 根据用户id删除用户组织
     *
     * @param id
     * @return
     */
    @Update("update SUC_USER set ORG_ID=NULL where ID=#{id,jdbcType=BIGINT}")
    int delUserOrgById(@Param("id") Long id);

    /**
     * 联查suc-org
     *
     * @param record
     * @return
     */
    List<SucUserJonintOrgSelectRo> selectjoint(@Param("users") String users);

    /**
     * 设置用户登录密码
     */
    @Update("update SUC_USER set LOGIN_PSWD=#{loginPswd,jdbcType=VARCHAR},PAY_PSWD=#{payPswd,jdbcType=VARCHAR},SALT=#{salt,jdbcType=VARCHAR} where ID=#{id,jdbcType=BIGINT}")
    int setLoginPw(SucUserMo record);

    /**
     * 查询用户信息
     *
     * @param users
     * @return
     */
    List<UserPointRo> listUserInformation(@Param("keys") String keys, @Param("orgId") Long orgId);

    /**
     * 根据领域id和关键字查询用户信息
     * 
     * @param domainId
     * @param keys
     * @return
     */
    List<SucUserMo> listUserByDomainIdAndKeys(@Param("domainId") String domainId, @Param("keys") String keys);

//    /**
//     * 根据邮箱获取多个用户信息
//     */
//    @Select("select ID, LOGIN_NAME, LOGIN_PSWD, SALT, IS_VERIFIED_EMAIL, NICKNAME, FACE, QQ_NICKNAME, QQ_FACE, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID,DOMAIN_ID from SUC_USER "
//            + " where lower(EMAIL) = lower(#{email,jdbcType=VARCHAR})")
//    List<SucUserMo> listByEmail(String email);
//
//    /**
//     * 根据手机号获取多个用户信息
//     */
//    @Select("select ID, LOGIN_NAME, LOGIN_PSWD, SALT, IS_VERIFIED_MOBILE, NICKNAME, FACE, QQ_NICKNAME, QQ_FACE, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID,DOMAIN_ID from SUC_USER "
//            + " where MOBILE = #{mobile,jdbcType=VARCHAR}")
//    List<SucUserMo> listByMobile(String mobile);
//
//    /**
//     * 根据用户登录名称获取多个用户信息
//     */
//    @Select("select ID, LOGIN_NAME, LOGIN_PSWD, SALT, NICKNAME, FACE, QQ_NICKNAME, QQ_FACE, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID,DOMAIN_ID from SUC_USER where lower(LOGIN_NAME) = lower(#{loginName,jdbcType=VARCHAR})")
//    List<SucUserMo> listByLoginName(String loginName);
}
