package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucUserForbiddenWordMo;

@Mapper
public interface SucUserForbiddenWordMapper extends MybatisBaseMapper<SucUserForbiddenWordMo, Long> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    int insert(SucUserForbiddenWordMo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    int insertSelective(SucUserForbiddenWordMo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    SucUserForbiddenWordMo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    int updateByPrimaryKeySelective(SucUserForbiddenWordMo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    int updateByPrimaryKey(SucUserForbiddenWordMo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    List<SucUserForbiddenWordMo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    List<SucUserForbiddenWordMo> selectSelective(SucUserForbiddenWordMo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    boolean existByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SUC_USER_FORBIDDEN_WORD
     *
     * @mbg.generated 2018-02-25 16:21:26
     */
    boolean existSelective(SucUserForbiddenWordMo record);
}