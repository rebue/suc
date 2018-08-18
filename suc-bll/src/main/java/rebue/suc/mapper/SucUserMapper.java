package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.SucUserJonintOrgSelectRo;

@Mapper
public interface SucUserMapper extends MybatisBaseMapper<SucUserMo, Long> {

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucUserMo record);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucUserMo record);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucUserMo selectByPrimaryKey(Long id);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucUserMo record);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucUserMo record);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucUserMo> selectAll();

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucUserMo> selectSelective(SucUserMo record);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
     *  自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucUserMo record);

    /**
     *  根据邮箱获取用户信息
     */
    @Select("select ID, LOGIN_NAME, LOGIN_PSWD, SALT, IS_VERIFIED_EMAIL, NICKNAME, FACE, QQ_NICKNAME, QQ_FACE, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID from SUC_USER " + " where lower(EMAIL) = lower(#{email,jdbcType=VARCHAR})")
    SucUserMo selectByEmail(String email);

    /**
     *  根据手机号获取用户信息
     */
    @Select("select ID, LOGIN_NAME, LOGIN_PSWD, SALT, IS_VERIFIED_MOBILE, NICKNAME, FACE, QQ_NICKNAME, QQ_FACE, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID from SUC_USER " + " where MOBILE = #{mobile,jdbcType=VARCHAR}")
    SucUserMo selectByMobile(String mobile);

    /**
     *  根据用户登录名称获取用户信息
     */
    @Select("select ID, LOGIN_NAME, LOGIN_PSWD, SALT, NICKNAME, FACE, QQ_NICKNAME, QQ_FACE, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID from SUC_USER where lower(LOGIN_NAME) = lower(#{loginName,jdbcType=VARCHAR})")
    SucUserMo selectByLoginName(String loginName);

    /**
     *  根据QQ的id获取用户信息
     */
    @Select("select ID, NICKNAME, QQ_NICKNAME, QQ_FACE, IS_LOCK from SUC_USER where QQ_ID = #{qqId,jdbcType=VARCHAR}")
    SucUserMo selectByQq(String qqId);
    
    /**
     *  根据QQ的id获取用户信息
     */
    @Select("select ID, NICKNAME, QQ_NICKNAME, QQ_FACE, IS_LOCK from SUC_USER where QQ_OPENID = #{qqOpenid,jdbcType=VARCHAR}")
    SucUserMo selectByQqopenId(String qqOpenid);

    /**
     *  根据微信的id获取用户信息
     */
    @Select("select ID, NICKNAME, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID from SUC_USER where WX_ID = #{wxId,jdbcType=VARCHAR}")
    SucUserMo selectByWx(String wxId);

    /**
     *  根据微信的openid获取用户信息
     */
    @Select("select ID, NICKNAME, WX_NICKNAME, WX_FACE, IS_LOCK, ORG_ID from SUC_USER where WX_OPENID = #{wxOpenid,jdbcType=VARCHAR}")
    SucUserMo selectByWxOpenid(String wxOpenid);

    /**
     *  锁定用户
     */
    @Update("update SUC_USER set IS_LOCK=1 where ID=#{id}")
    int lock(Long id);

    /**
     *  解锁用户
     */
    @Update("update SUC_USER set IS_LOCK=0 where ID=#{id}")
    int unlock(Long id);

    /**
     *  根据微信ID查询登录密码、密码组合码、微信ID Title: selectUserInfoByWx Description:
     *
     *  @param wxId
     *  @return
     *  @date 2018年4月28日 上午11:47:26
     */
    @Select("SELECT LOGIN_PSWD, SALT, WX_ID FROM SUC_USER WHERE WX_ID = #{wxId,jdbcType=VARCHAR}")
    SucUserMo selectUserInfoByWx(@Param("wxId") String wxId);

    /**
     *  根据微信ID修改登录密码 Title: updateloginPswd Description:
     *
     *  @param wxId
     *  @param loginPswd
     *  @return
     *  @date 2018年4月28日 上午11:57:49
     */
    @Update("UPDATE SUC_USER SET LOGIN_PSWD = #{loginPswd,jdbcType=VARCHAR} WHERE WX_ID = #{wxId,jdbcType=VARCHAR}")
    int updateloginPswd(@Param("wxId") String wxId, @Param("loginPswd") String loginPswd);

    /**
     *  根据微信ID设置登录密码 Title: setLoginPswd Description:
     *
     *  @param wxId
     *  @param loginPswd
     *  @param salt
     *  @return
     *  @date 2018年4月28日 上午11:59:04
     */
    @Update("UPDATE SUC_USER SET LOGIN_PSWD = #{loginPswd,jdbcType=VARCHAR}, SALT = #{salt,jdbcType=VARCHAR} WHERE WX_ID = #{wxId,jdbcType=VARCHAR}")
    int setLoginPswd(@Param("wxId") String wxId, @Param("loginPswd") String loginPswd, @Param("salt") String salt);

    /**
     *  根据微信ID设置登录名称 Title: setLoginName Description:
     *
     *  @param wxId
     *  @param loginName
     *  @return
     *  @date 2018年5月3日 下午5:30:31
     */
    @Update("UPDATE SUC_USER SET LOGIN_NAME = #{loginName,jdbcType=VARCHAR} WHERE WX_ID = #{wxId,jdbcType=VARCHAR}")
    int setLoginName(@Param("wxId") String wxId, @Param("loginName") String loginName);

    /**
     *  根据微信ID获取用户登录名称 Title: selectLoginNameByWx Description:
     *
     *  @param wxId
     *  @return
     *  @date 2018年5月4日 上午9:02:28
     */
    @Select("SELECT LOGIN_NAME FROM SUC_USER WHERE WX_ID = #{wxId,jdbcType=VARCHAR}")
    String selectLoginNameByWx(@Param("wxId") String wxId);

    /**
     *  设置用户锁定/解锁
     *
     *  @param id
     *  @param isLock
     *  @return
     */
    @Update("update SUC_USER set IS_LOCK=#{isLock,jdbcType=TINYINT} where ID=#{id,jdbcType=BIGINT}")
    int lockOrUnlockById(@Param("id") Long id, @Param("isLock") Boolean isLock);

    /**
     *  根据id解除登录密码
     *
     *  @param id
     *  @return
     */
    @Update("UPDATE SUC_USER SET LOGIN_PSWD=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int removeLoginPassWord(@Param("id") Long id);

    /**
     *  根据用户id解除支付密码
     *
     *  @param id
     *  @return
     */
    @Update("UPDATE SUC_USER SET PAY_PSWD=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int removePayPassWord(@Param("id") Long id);

    /**
     *  根据用户id解绑微信
     *
     *  @param id
     *  @return
     */
    @Update("UPDATE SUC_USER SET WX_ID=NULL, WX_NICKNAME=NULL, WX_FACE=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int unbindWeChat(@Param("id") Long id);

    /**
     *  根据用户id解绑微信
     *
     *  @param id
     *  @return
     */
    @Update("UPDATE SUC_USER SET QQ_ID=NULL, QQ_NICKNAME=NULL, QQ_FACE=NULL WHERE ID=#{id,jdbcType=BIGINT}")
    int unbindQQ(@Param("id") Long id);

    /**
     *  多条件同时查询
     *
     *  @param users
     *  @return
     */
    List<SucUserMo> selectMeanwhile(@Param("users") String users);

    /**
     *  根据用户id批量查询用户名称
     *
     *  @param ids
     *  @return
     */
    List<SucUserMo> selectByIds(@Param("ids") String ids);

    /**
     *  根据组织编号删除用户组织
     *  @param orgId
     *  @return
     */
    @Update("update SUC_USER set ORG_ID=NULL where ORG_ID=#{orgId,jdbcType=BIGINT}")
    int delUserOrgByOrgId(@Param("orgId") Long orgId);
    
    /**
     * 添加用户组织
     * @param id
     * @param orgId
     * @return
     */
    @Update("update SUC_USER set ORG_ID=#{orgId,jdbcType=BIGINT} where ID=#{id,jdbcType=BIGINT}")
    int insertOrgById(@Param("id") Long id, @Param("orgId") Long orgId);
    
    /**
     * 根据用户id删除用户组织
     * @param id
     * @return
     */
    @Update("update SUC_USER set ORG_ID=NULL where ID=#{id,jdbcType=BIGINT}")
    int delUserOrgById(@Param("id") Long id);
    
    /**
     * 联查suc-org
     * @param record
     * @return
     */
    List<SucUserJonintOrgSelectRo> selectjoint(@Param("users") String users);
}
