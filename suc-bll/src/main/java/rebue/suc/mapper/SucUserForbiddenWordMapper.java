package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucUserForbiddenWordMo;

@Mapper
public interface SucUserForbiddenWordMapper extends MybatisBaseMapper<SucUserForbiddenWordMo, Long> {

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucUserForbiddenWordMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucUserForbiddenWordMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucUserForbiddenWordMo selectByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucUserForbiddenWordMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucUserForbiddenWordMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucUserForbiddenWordMo> selectAll();

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucUserForbiddenWordMo> selectSelective(SucUserForbiddenWordMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucUserForbiddenWordMo record);
}
