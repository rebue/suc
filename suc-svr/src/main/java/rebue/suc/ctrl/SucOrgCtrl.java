package rebue.suc.ctrl;

import com.github.pagehelper.PageInfo;

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
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.SucOrgRo;
import rebue.suc.svc.SucOrgSvc;

@RestController
public class SucOrgCtrl {

	/**
	 * @mbg.generated
	 */
	private static final Logger _log = LoggerFactory.getLogger(SucOrgCtrl.class);

	/**
	 * @mbg.generated
	 */
	@Resource
	private SucOrgSvc svc;

	/**
	 * 有唯一约束的字段名称
	 *
	 * @mbg.generated
	 */
	private String _uniqueFilesName = "某字段内容";

	/**
	 * 添加公司/组织信息
	 *
	 * @mbg.generated
	 */
	@PostMapping("/suc/org")
	SucOrgRo add(@RequestBody SucOrgMo mo) throws Exception {
		_log.info("add SucOrgMo:" + mo);
		SucOrgRo ro = new SucOrgRo();
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
	 * 修改公司/组织信息
	 *
	 * @mbg.generated
	 */
	@PutMapping("/suc/org")
	SucOrgRo modify(@RequestBody SucOrgMo mo) throws Exception {
		_log.info("modify SucOrgMo:" + mo);
		SucOrgRo ro = new SucOrgRo();
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
	 * 查询公司/组织信息
	 *
	 * @mbg.generated
	 */
	@GetMapping("/suc/org")
	PageInfo<SucOrgMo> list(SucOrgMo mo, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
		_log.info("list SucOrgMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SucOrgMo> result = svc.list(mo, pageNum, pageSize);
		_log.info("result: " + result);
		return result;
	}

	/**
	 * 获取单个公司/组织信息
	 *
	 * @mbg.generated
	 */
	@GetMapping("/suc/org/getbyid")
	SucOrgRo getById(@RequestParam("id") java.lang.Long id) {
		_log.info("get SucOrgMo by id: " + id);
		SucOrgMo result = svc.getById(id);
		_log.info("get: " + result);
		SucOrgRo ro = new SucOrgRo();
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

	/**
	 * 删除公司/组织信息
	 *
	 * @mbg.overrideByMethodName
	 */
	@DeleteMapping("/suc/org")
	SucOrgRo del(@RequestParam("id") java.lang.Long id) {
		_log.info("save SucOrgMo:" + id);
		SucOrgRo ro = new SucOrgRo();
		try {
			return svc.delEx(id);
		} catch (Exception e) {
			String msg = "删除失败，找不到该记录";
			_log.error("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult((byte) -1);
			return ro;
		}
	}

	/**
	 * 根据组织名称查询组织信息
	 * @param name
	 * @return
	 */
	@GetMapping("/suc/org/selectbyname")
	List<SucOrgMo> selectByName(@RequestParam(value = "name", required = false) String name) {
		_log.info("查询组织信息的参数为：{}", name);
		return svc.selectByName(name);
	}
}
