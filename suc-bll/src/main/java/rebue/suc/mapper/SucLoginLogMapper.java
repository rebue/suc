package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucLoginLogMo;

@Mapper
public interface SucLoginLogMapper extends MybatisBaseMapper<SucLoginLogMo, Long> {

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucLoginLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucLoginLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucLoginLogMo selectByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucLoginLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucLoginLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucLoginLogMo> selectAll();

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucLoginLogMo> selectSelective(SucLoginLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucLoginLogMo record);
}
