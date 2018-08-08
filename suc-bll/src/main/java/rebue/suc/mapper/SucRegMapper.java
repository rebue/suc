package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucRegMo;

@Mapper
public interface SucRegMapper extends MybatisBaseMapper<SucRegMo, Long> {

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucRegMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucRegMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucRegMo selectByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucRegMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucRegMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucRegMo> selectAll();

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucRegMo> selectSelective(SucRegMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucRegMo record);
}
