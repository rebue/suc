package rebue.suc.ctrl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.ro.UsersRo;
import rebue.suc.svc.SucUserOrgSvc;
import rebue.suc.to.OrgMoveUsersTo;

@RestController
public class SucUserOrgCtrl {

    private static final Logger _log = LoggerFactory.getLogger(SucUserOrgCtrl.class);

    @Resource
    private SucUserOrgSvc       svc;

    /**
     * 添加用户到组织中
     */
    @PostMapping("/suc/userorg")
    UsersRo add(@RequestBody OrgMoveUsersTo to) {
        _log.info("add userorg: {}", to);
        svc.add(to.getId(), to.getMoveIds());
        return svc.listAddedAndUnaddedUsers(to.getId(), 7, to.getAddedKeys(), to.getAddedPageNum(), to.getUnaddedKeys(), to.getUnaddedPageNum());
    }

    /**
     * 从组织中移除用户
     */
    @DeleteMapping("/suc/userorg")
    UsersRo del(@RequestBody OrgMoveUsersTo to) {
        _log.info("del userorg: {}", to);
        svc.del(to.getMoveIds());
        return svc.listAddedAndUnaddedUsers(to.getId(), 7, to.getAddedKeys(), to.getAddedPageNum(), to.getUnaddedKeys(), to.getUnaddedPageNum());
    }

    /**
     * 查询指定组织的已添加和未添加的用户列表
     * 
     * @param id
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @GetMapping("/suc/userorg/listaddedusers")
    UsersRo listAddedUsers(@RequestParam("id") Long id, @RequestParam(value = "keys", required = false) String keys,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 7;
        _log.info("list id:" + id + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        PageInfo<SucUserDetailRo> added = svc.listAddedUsers(id, keys, pageNum, pageSize);
        _log.info("added: " + added);
        UsersRo ro = new UsersRo();
        ro.setAddedSucUsers(added);
        return ro;
    }

    /**
     * 查询指定组织的已添加和未添加的用户列表
     * 
     * @param id
     *            组织ID
     * @param keys
     *            模糊查询用户的关键字
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @GetMapping("/suc/userorg/listunaddedusers")
    UsersRo listUnaddedUsers(@RequestParam("id") Long id, @RequestParam(value = "keys", required = false) String keys,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 7;
        _log.info("list id:" + id + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        PageInfo<SucUserDetailRo> unadded = svc.listUnaddedUsers(id, keys, pageNum, pageSize);
        _log.info("unadded: " + unadded);
        UsersRo ro = new UsersRo();
        ro.setUnaddedSucUsers(unadded);
        return ro;
    }

    /**
     * 查询指定组织的已添加和未添加的用户列表
     * 
     * @param id
     *            组织ID
     * @param pageNum
     *            第几页
     * @param pageSize
     *            每页大小
     */
    @GetMapping("/suc/userorg/listaddedandunaddedusers")
    UsersRo listAddedAndUnaddedUsers(@RequestParam("id") Long id, @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 7;
        _log.info("list id:" + id + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        return svc.listAddedAndUnaddedUsers(id, pageSize, null, pageNum, null, pageNum);
    }
}
