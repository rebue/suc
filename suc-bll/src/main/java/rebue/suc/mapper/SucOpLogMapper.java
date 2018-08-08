package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucOpLogMo;

@Mapper
public interface SucOpLogMapper extends MybatisBaseMapper<SucOpLogMo, Long> {

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucOpLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucOpLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucOpLogMo selectByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucOpLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucOpLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucOpLogMo> selectAll();

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucOpLogMo> selectSelective(SucOpLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucOpLogMo record);
}
