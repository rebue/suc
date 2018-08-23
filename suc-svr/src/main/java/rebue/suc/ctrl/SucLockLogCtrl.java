package rebue.suc.ctrl;

import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebue.suc.mo.SucLockLogMo;
import rebue.suc.ro.SucLockLogRo;
import rebue.suc.svc.SucLockLogSvc;

@RestController
public class SucLockLogCtrl {

    /**
     * @mbg.generated
     */
    private static final Logger _log = LoggerFactory.getLogger(SucLockLogCtrl.class);

    /**
     * @mbg.generated
     */
    @Resource
    private SucLockLogSvc svc;

    /**
     * 有唯一约束的字段名称
     *
     * @mbg.generated
     */
    private String _uniqueFilesName = "某字段内容";

    /**
     * 添加用户锁定日志
     *
     * @mbg.generated
     */
    @PostMapping("/suc/locklog")
    SucLockLogRo add(@RequestBody SucLockLogMo mo) throws Exception {
        _log.info("add SucLockLogMo:" + mo);
        SucLockLogRo ro = new SucLockLogRo();
        try {
            int result = svc.add(mo);
            if (result == 1) {
                String msg = "添加成功";
                _log.info("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) 1);
                return ro;
            } else {
                String msg = "添加失败";
                _log.error("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) -1);
                return ro;
            }
        } catch (DuplicateKeyException e) {
            String msg = "添加失败，" + _uniqueFilesName + "已存在，不允许出现重复";
            _log.error("{}: mo-{}", msg, mo);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        }
    }

    /**
     * 修改用户锁定日志
     *
     * @mbg.generated
     */
    @PutMapping("/suc/locklog")
    SucLockLogRo modify(@RequestBody SucLockLogMo mo) throws Exception {
        _log.info("modify SucLockLogMo:" + mo);
        SucLockLogRo ro = new SucLockLogRo();
        try {
            int result = svc.modify(mo);
            if (result == 1) {
                String msg = "修改成功";
                _log.info("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) 1);
                return ro;
            } else {
                String msg = "修改失败";
                _log.error("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult((byte) -1);
                return ro;
            }
        } catch (DuplicateKeyException e) {
            String msg = "修改失败，" + _uniqueFilesName + "已存在，不允许出现重复";
            _log.error("{}: mo-{}", msg, mo);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        }
    }

    /**
     * 删除用户锁定日志
     *
     * @mbg.generated
     */
    @DeleteMapping("/suc/locklog")
    SucLockLogRo del(@RequestParam("id") java.lang.Long id) {
        _log.info("save SucLockLogMo:" + id);
        int result = svc.del(id);
        SucLockLogRo ro = new SucLockLogRo();
        if (result == 1) {
            String msg = "删除成功";
            _log.info("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) 1);
            return ro;
        } else {
            String msg = "删除失败，找不到该记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        }
    }

    /**
     * 查询用户锁定日志
     *
     * @mbg.generated
     */
    @GetMapping("/suc/locklog")
    PageInfo<SucLockLogMo> list(SucLockLogMo mo, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        _log.info("list SucLockLogMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        PageInfo<SucLockLogMo> result = svc.list(mo, pageNum, pageSize);
        _log.info("result: " + result);
        return result;
    }

    /**
     * 获取单个用户锁定日志
     *
     * @mbg.generated
     */
    @GetMapping("/suc/locklog/getbyid")
    SucLockLogRo getById(@RequestParam("id") java.lang.Long id) {
        _log.info("get SucLockLogMo by id: " + id);
        SucLockLogMo result = svc.getById(id);
        _log.info("get: " + result);
        SucLockLogRo ro = new SucLockLogRo();
        if (result == null) {
            String msg = "获取失败，没有找到该条记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) -1);
            return ro;
        } else {
            String msg = "获取成功";
            _log.info("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult((byte) 1);
            ro.setRecord(result);
            return ro;
        }
    }
}
