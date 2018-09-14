package rebue.suc.svc;

import java.util.List;

import com.github.pagehelper.PageInfo;

import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.UsersRo;

public interface SucUserOrgSvc {

    /**
     * 添加用户到组织中
     */
    public int add(Long orgId, List<Long> moveIds);

    /**
     * 从组织中移除用户
     */
    public int del(List<Long> moveIds);

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
    UsersRo listAddedAndUnaddedUsers(Long orgId, Integer pageSize, String addedKeys, Integer addedPageNum, String unaddedKeys, Integer unaddedPageNum);

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
    PageInfo<SucUserDetailRo> listAddedUsers(Long orgId, String keys, Integer pageNum, Integer pageSize);

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
    PageInfo<SucUserDetailRo> listUnaddedUsers(Long orgId, String keys, Integer pageNum, Integer pageSize);

}
