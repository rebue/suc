package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucLockLogMo;

@Mapper
public interface SucLockLogMapper extends MybatisBaseMapper<SucLockLogMo, Long> {

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucLockLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucLockLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucLockLogMo selectByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucLockLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucLockLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucLockLogMo> selectAll();

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucLockLogMo> selectSelective(SucLockLogMo record);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucLockLogMo record);
}
