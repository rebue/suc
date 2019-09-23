package rebue.suc.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import rebue.suc.msg.SucAddOrgDoneMsg;
import rebue.suc.pub.SucAddOrgDonePub;
import rebue.suc.ro.SucOrgInOrNotInRo;
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

    @Resource
    private SucAddOrgDonePub orgAddPub;

//    @Resource
//    AfcAccountSvc afcAccountSvc;
//
//    @Resource
//    AfcTradeSvc afcTradeSvc;
//
//    @Resource
//    OrdOrderSvc ordOrderSvc;

    /**
     * @mbg.overrideByMethodName
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(final SucOrgMo mo) {
//        if (mo.getShortName() == null) {
//            return -1;
//        }
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        if (mo.getCreateTimestamp() == null || mo.getCreateTimestamp() == 0) {
            mo.setCreateTimestamp(System.currentTimeMillis());
        }
        final int result = super.add(mo);
        if (result == 1) {
            // 发布添加用户的消息
            final SucAddOrgDoneMsg msg = new SucAddOrgDoneMsg();
            msg.setId(mo.getId());
            orgAddPub.send(msg);
        }
        return result;
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
    public int del(final Long id) {
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
    public PageInfo<SucOrgMo> list(final String keys, final int pageNum, final int pageSize) {
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
    public List<SucOrgMo> listByName(final String name) {
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
    public SucOrgRo enable(final Long id, final Boolean isEnabled) {
        final SucOrgRo ro = new SucOrgRo();
        _log.info("禁用或者启用组织的参数为：id={}, isEnabled={}", id, isEnabled);
        final int enableResult = _mapper.enable(id, isEnabled);
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

//    @Override
//    public PageInfo<OrgAccountRo> listOrgAccount(final SucOrgMo mo, final int pageNum, final int pageSize) {
//        _log.info("查询组织的参数SucOrgMo：{} pageNum-{} pageSize-{} ", mo, pageNum, pageSize);
//        final PageInfo<OrgAccountRo> result = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectOrg(mo));
//        _log.info("查询组织的参数返回的结果是 {}", result);
//        for (final OrgAccountRo item : result.getList()) {
//            _log.info("获取供应商账户余额和订单结算信息循环开始----------------------------------------");
//            // 获取供应商账户
//            _log.info("获取供应商账户的参数是-id-{}", item.getSupplierId());
//            final AfcAccountMo AfcAccountResult = afcAccountSvc.getById(item.getSupplierId());
//            _log.info("获取供应商账户的结果是-AfcAccountResult-{}", AfcAccountResult);
//            if (AfcAccountResult != null) {
//                item.setBalance(AfcAccountResult.getBalance());
//                item.setWithdrawing(AfcAccountResult.getWithdrawing());
//            }
//            // 获取供应商已经提现总额
//            _log.info("获取供应商已经提现总额的参数是-id-{}", item.getSupplierId());
//            final OrgWithdrawRo orgWithdrawRo = afcTradeSvc.getOrgWithdrawTotal(item.getSupplierId());
//            _log.info("获取供应商已经提现总额的结果是-orgWithdrawRo-{}", orgWithdrawRo);
//            if (orgWithdrawRo != null) {
//                item.setWithdrawTotal(orgWithdrawRo.getWithdrawTotal());
//            }
//            // 获取供应商已经结算和待结算总成本
//            _log.info("获取供应商订单待结算和已结算成本的参数为 SupplierId-{} ", item.getSupplierId());
//            final OrdSettleRo ordSettleResult = ordOrderSvc.getSettleTotal(item.getSupplierId());
//            _log.info("获取供应商订单待结算和已结算成本的结果为 ordSettleResult-{} ", ordSettleResult);
//            if (ordSettleResult != null) {
//                if (ordSettleResult.getAlreadySettle() != null) {
//                    item.setAlreadySettle(ordSettleResult.getAlreadySettle());
//                }
//                if (ordSettleResult.getNotSettle() != null) {
//                    item.setNotSettle(ordSettleResult.getNotSettle());
//                }
//            }
//            _log.info("获取供应商账户余额和订单结算信息循环开结束+++++++++++++++++++++++++++++++++++++++");
//
//        }
//        return result;
//    }

    @Override
    public SucOrgInOrNotInRo listAddedAndUnaddedOrgs(final String orgIds, final Integer pageSize, final String addedKeys, final Integer addedPageNum, final String unaddedKeys,
            final Integer unaddedPageNum) {
        final SucOrgInOrNotInRo ro = new SucOrgInOrNotInRo();
        // 获取已添加组织列表
        final PageInfo<SucOrgMo> added = listAddedOrgs(orgIds, addedKeys, addedPageNum, pageSize);
        _log.info("added: " + added);
        ro.setAddedOrgs(added);
        // 获取未添加组织列表
        final PageInfo<SucOrgMo> unadded = listUnaddedOrgs(orgIds, unaddedKeys, unaddedPageNum, pageSize);
        _log.info("unadded: " + unadded);
        ro.setUnaddedOrgs(unadded);
        return ro;
    }

    @Override
    public PageInfo<SucOrgMo> listAddedOrgs(final String orgIds, final String keys, final Integer pageNum, final Integer pageSize) {
        _log.info("查询指定组织的已添加的组织列表：orgId-{},keys-{},pageNum-{},pageSize-{}", orgIds, keys, pageNum, pageSize);
        if (StringUtils.isBlank(keys)) {
            return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectAddedOrgsByOrgIds(orgIds));
        } else {
            return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectAddedOrgsByOrgIdsAndKeys(orgIds, keys));
        }

    }

    @Override
    public PageInfo<SucOrgMo> listUnaddedOrgs(final String orgIds, final String keys, final Integer pageNum, final Integer pageSize) {
        _log.info("查询指定组织的没有添加的组织列表：orgId-{}", orgIds);
        if (StringUtils.isBlank(keys)) {
            return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectUnaddedOrgsByOrgIds(orgIds));
        } else {
            return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> _mapper.selectUnaddedOrgsByOrgIdsAndKeys(orgIds, keys));
        }
    }

    /**
     * 根据组织编号模糊查询组织id
     */
    @Override
    public List<SucOrgMo> selectIdByOrgCode(final String orgCode) {
        _log.info("根据组织编号模糊查询组织id: orgCode-{}", orgCode);
        final List<SucOrgMo> result = _mapper.selectIdByOrgCode(orgCode);
        _log.info("根据组织编号模糊查询组织id返回的结果为: orgCodeResult-{}", result);
        return result;
    }

    /**
     * 根据组织名称模糊查询组织id
     */
    @Override
    public List<SucOrgMo> selectIdByName(final String name) {
        _log.info("根据组织名称模糊查询组织id: orgCode-{}", name);
        final List<SucOrgMo> result = _mapper.selectIdByName(name);
        _log.info("根据组织名称模糊查询组织id返回的结果为: OrgnameResult-{}", result);
        return result;
    }

}
