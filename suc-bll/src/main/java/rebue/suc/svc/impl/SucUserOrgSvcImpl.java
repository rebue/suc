package rebue.suc.svc.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.suc.mapper.SucUserOrgMapper;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.UsersRo;
import rebue.suc.svc.SucUserOrgSvc;
import rebue.wheel.ListUtils;

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
public class SucUserOrgSvcImpl implements SucUserOrgSvc {
    private static final Logger _log = LoggerFactory.getLogger(SucUserOrgSvcImpl.class);

    @Autowired
    protected SucUserOrgMapper  userOrgMapper;

    /**
     * 添加用户到组织中
     */
    @Override
    public int add(Long orgId, List<Long> moveIds) {
        String userIds = ListUtils.toString(moveIds);
        return userOrgMapper.addUsers(orgId, userIds);
    }

    /**
     * 从组织中移除用户
     */
    @Override
    public int del(List<Long> moveIds) {
        String userIds = ListUtils.toString(moveIds);
        return userOrgMapper.delUsers(userIds);
    }

    /**
     * 查询指定组织的已添加和未添加用户的列表
     * 
     * @param orgId
     *            组织ID
     * @param pageSize
     *            每页大小
     * @param addedKeys
     *            模糊查询已添加用户的关键字
     * @param addedPageNum
     *            已添加用户是第几页
     * @param unaddedKeys
     *            模糊查询未添加用户的关键字
     * @param unaddedPageNum
     *            未添加用户是第几页
     */
    @Override
    public UsersRo listAddedAndUnaddedUsers(Long orgId, Integer pageSize, String addedKeys, Integer addedPageNum, String unaddedKeys, Integer unaddedPageNum) {
        UsersRo ro = new UsersRo();
        // 获取已添加用户列表
        PageInfo<SucUserDetailRo> added = listAddedUsers(orgId, addedKeys, addedPageNum, pageSize);
        _log.info("added: " + added);
        ro.setAddedSucUsers(added);
        // 获取未添加用户列表
        PageInfo<SucUserDetailRo> unadded = listUnaddedUsers(orgId, unaddedKeys, unaddedPageNum, pageSize);
        _log.info("unadded: " + unadded);
        ro.setUnaddedSucUsers(unadded);
        return ro;
    }

    /**
     * 查询指定组织的已添加的用户列表
     * 
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @Override
    public PageInfo<SucUserDetailRo> listAddedUsers(Long orgId, String keys, Integer pageNum, Integer pageSize) {
        _log.info("查询指定组织的已添加的用户列表：orgId-{}", orgId);
        if (keys == null)
            return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> userOrgMapper.selectAddedUsersByOrgId(orgId));
        else
            return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> userOrgMapper.selectAddedUsersByOrgIdAndKeys(orgId, keys));
    }

    /**
     * 查询指定组织的未添加的用户列表
     * 
     * @param orgId
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @Override
    public PageInfo<SucUserDetailRo> listUnaddedUsers(Long orgId, String keys, Integer pageNum, Integer pageSize) {
        _log.info("查询指定组织的已添加的用户列表：orgId-{}", orgId);
        if (keys == null)
            return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> userOrgMapper.selectUnaddedUsersByOrgId(orgId));
        else
            return PageHelper.startPage(pageNum, pageSize, "MODIFIED_TIMESTAMP DESC").doSelectPageInfo(() -> userOrgMapper.selectUnaddedUsersByOrgIdAndKeys(orgId, keys));
    }

}
