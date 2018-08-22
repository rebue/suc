package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucUserForbiddenWordMo;

@Mapper
public interface SucUserForbiddenWordMapper extends MybatisBaseMapper<SucUserForbiddenWordMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(SucUserForbiddenWordMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(SucUserForbiddenWordMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    SucUserForbiddenWordMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(SucUserForbiddenWordMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(SucUserForbiddenWordMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SucUserForbiddenWordMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SucUserForbiddenWordMo> selectSelective(SucUserForbiddenWordMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(SucUserForbiddenWordMo record);
}
