package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucDomainMo;

@Mapper
public interface SucDomainMapper extends MybatisBaseMapper<SucDomainMo, String> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(String id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(SucDomainMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(SucDomainMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    SucDomainMo selectByPrimaryKey(String id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(SucDomainMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(SucDomainMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SucDomainMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SucDomainMo> selectSelective(SucDomainMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(String id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(SucDomainMo record);
}
