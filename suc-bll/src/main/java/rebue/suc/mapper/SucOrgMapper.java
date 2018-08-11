package rebue.suc.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.suc.mo.SucOrgMo;

@Mapper
public interface SucOrgMapper extends MybatisBaseMapper<SucOrgMo, Long> {
    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insert(SucOrgMo record);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int insertSelective(SucOrgMo record);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    SucOrgMo selectByPrimaryKey(Long id);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKeySelective(SucOrgMo record);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    int updateByPrimaryKey(SucOrgMo record);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucOrgMo> selectAll();

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    List<SucOrgMo> selectSelective(SucOrgMo record);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existByPrimaryKey(Long id);

    /**
    自动生成，如需修改，请删除本行 @mbg.generated
     */
    boolean existSelective(SucOrgMo record);
}