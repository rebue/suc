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
import rebue.suc.mo.SucUserForbiddenWordMo;
import rebue.suc.ro.SucUserForbiddenWordRo;
import rebue.suc.svc.SucUserForbiddenWordSvc;

@RestController
public class SucUserForbiddenWordCtrl {

    /**
     * @mbg.generated
     */
    private static final Logger _log = LoggerFactory.getLogger(SucUserForbiddenWordCtrl.class);

    /**
     * @mbg.generated
     */
    @Resource
    private SucUserForbiddenWordSvc svc;

    /**
     * 有唯一约束的字段名称
     *
     * @mbg.generated
     */
    private String _uniqueFilesName = "某字段内容";

    /**
     * 添加用户名敏感词
     *
     * @mbg.generated
     */
    @PostMapping("/suc/userforbiddenword")
    SucUserForbiddenWordRo add(@RequestBody SucUserForbiddenWordMo mo) throws Exception {
        _log.info("add SucUserForbiddenWordMo:" + mo);
        SucUserForbiddenWordRo ro = new SucUserForbiddenWordRo();
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
     * 修改用户名敏感词
     *
     * @mbg.generated
     */
    @PutMapping("/suc/userforbiddenword")
    SucUserForbiddenWordRo modify(@RequestBody SucUserForbiddenWordMo mo) throws Exception {
        _log.info("modify SucUserForbiddenWordMo:" + mo);
        SucUserForbiddenWordRo ro = new SucUserForbiddenWordRo();
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
     * 删除用户名敏感词
     *
     * @mbg.generated
     */
    @DeleteMapping("/suc/userforbiddenword")
    SucUserForbiddenWordRo del(@RequestParam("id") java.lang.Long id) {
        _log.info("save SucUserForbiddenWordMo:" + id);
        int result = svc.del(id);
        SucUserForbiddenWordRo ro = new SucUserForbiddenWordRo();
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
     * 查询用户名敏感词
     *
     * @mbg.generated
     */
    @GetMapping("/suc/userforbiddenword")
    PageInfo<SucUserForbiddenWordMo> list(SucUserForbiddenWordMo mo, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        _log.info("list SucUserForbiddenWordMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        PageInfo<SucUserForbiddenWordMo> result = svc.list(mo, pageNum, pageSize);
        _log.info("result: " + result);
        return result;
    }

    /**
     * 获取单个用户名敏感词
     *
     * @mbg.generated
     */
    @GetMapping("/suc/userforbiddenword/getbyid")
    SucUserForbiddenWordRo getById(@RequestParam("id") java.lang.Long id) {
        _log.info("get SucUserForbiddenWordMo by id: " + id);
        SucUserForbiddenWordMo result = svc.getById(id);
        _log.info("get: " + result);
        SucUserForbiddenWordRo ro = new SucUserForbiddenWordRo();
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
