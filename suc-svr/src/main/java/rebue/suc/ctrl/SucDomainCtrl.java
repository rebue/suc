package rebue.suc.ctrl;

import java.util.List;

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

import rebue.suc.mo.SucDomainMo;
import rebue.suc.ro.SucDomainRo;
import rebue.suc.svc.SucDomainSvc;

@RestController
public class SucDomainCtrl {

    /**
     * @mbg.generated
     */
    private static final Logger _log = LoggerFactory.getLogger(SucDomainCtrl.class);

    /**
     * @mbg.generated
     */
    @Resource
    private SucDomainSvc svc;

    /**
     * 有唯一约束的字段名称
     *
     * @mbg.generated
     */
    private String _uniqueFilesName = "某字段内容";

    /**
     * 添加领域信息
     *
     * @mbg.generated
     */
    @PostMapping("/suc/domain")
    SucDomainRo add(@RequestBody SucDomainMo mo) throws Exception {
        _log.info("add SucDomainMo:" + mo);
        SucDomainRo ro = new SucDomainRo();
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
     * 修改领域信息
     *
     * @mbg.generated
     */
    @PutMapping("/suc/domain")
    SucDomainRo modify(@RequestBody SucDomainMo mo) throws Exception {
        _log.info("modify SucDomainMo:" + mo);
        SucDomainRo ro = new SucDomainRo();
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
     * 删除领域信息
     *
     * @mbg.generated
     */
    @DeleteMapping("/suc/domain")
    SucDomainRo del(@RequestParam("id") java.lang.String id) {
        _log.info("save SucDomainMo:" + id);
        int         result = svc.del(id);
        SucDomainRo ro     = new SucDomainRo();
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
     * 查询领域信息
     *
     * @mbg.overrideByMethodName
     */
    @GetMapping("/suc/domain")
    List<SucDomainMo> list() {
        _log.info("查询领域信息所有");
        List<SucDomainMo> result = svc.listAll();
        _log.info("result: " + result);
        return result;
    }

    /**
     * 获取单个领域信息
     *
     * @mbg.generated
     */
    @GetMapping("/suc/domain/getbyid")
    SucDomainRo getById(@RequestParam("id") java.lang.String id) {
        _log.info("get SucDomainMo by id: " + id);
        SucDomainMo result = svc.getById(id);
        _log.info("get: " + result);
        SucDomainRo ro = new SucDomainRo();
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
