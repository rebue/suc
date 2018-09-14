package rebue.suc.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.impl.MybatisBaseSvcImpl;
import rebue.suc.mapper.SucOrgMapper;
import rebue.suc.mapper.SucUserMapper;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.SucOrgRo;
import rebue.suc.svc.SucOrgSvc;

@Service
/**
 * <pre>
 * 在单独使用不带任何参数 的 @Transactional 注释时，
 * propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED，
 * 而且事务不会针对受控异常（checked exception）回滚。
 * 注意：
 * 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * </pre>
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SucOrgSvcImpl extends MybatisBaseSvcImpl<SucOrgMo, java.lang.Long, SucOrgMapper> implements SucOrgSvc {

    /**
     * @mbg.overrideByMethodName
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(SucOrgMo mo) {
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        if (mo.getCreateTimestamp() == null || mo.getCreateTimestamp() == 0) {
            mo.setCreateTimestamp(System.currentTimeMillis());
        }
        return super.add(mo);
    }

    private static final Logger _log = LoggerFactory.getLogger(SucOrgSvcImpl.class);

    @Resource
    private SucUserMapper       sucUserMapper;

    /**
     * 删除组织
     *
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int del(Long id) {
        _log.info("删除组织的ID为：{}", id);
        _log.info("删除用户组织");
        sucUserMapper.delUserOrgByOrgId(id);
        return super.del(id);
    }

    /**
     * 分页模糊查询组织
     * 
     * @param keys
     *            模糊查询的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @Override
    public PageInfo<SucOrgMo> list(String keys, int pageNum, int pageSize) {
        _log.info("分页模糊查询组织：{}", keys);
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectByKeys(keys));
    }

    /**
     * 根据组织名称查询组织信息
     * 
     * @param name
     * @return
     */
    @Override
    public List<SucOrgMo> listByName(String name) {
        _log.info("根据组织名称查询组织信息的参数为：{}", name);
        return _mapper.selectByName(name);
    }

    /**
     * 设置禁用或者启用组织
     * 
     * @param id
     * @param isEnabled
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public SucOrgRo enable(Long id, Boolean isEnabled) {
        SucOrgRo ro = new SucOrgRo();
        _log.info("禁用或者启用组织的参数为：id={}, isEnabled={}", id, isEnabled);
        int enableResult = _mapper.enable(id, isEnabled);
        _log.info("禁用或者启用组织的返回值为：{}", enableResult);
        if (enableResult != 1) {
            _log.error("禁用或者启用组织失败，id为：{}", id);
            ro.setResult((byte) -1);
            ro.setMsg("设置失败");
            return ro;
        }
        _log.info("禁用或者启用组织成功，id为：{}", id);
        ro.setResult((byte) 1);
        ro.setMsg("设置成功");
        return ro;
    }

}
