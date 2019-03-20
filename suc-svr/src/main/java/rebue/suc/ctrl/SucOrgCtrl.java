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

import com.github.pagehelper.PageInfo;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.OrgAccountRo;
import rebue.suc.ro.SucOrgInOrNotInRo;
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
	 * 删除组织信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/suc/org")
	Ro del(@RequestParam("id") java.lang.Long id) {
		_log.info("del SucOrgMo by id: {}", id);
		int result = svc.del(id);
		Ro ro = new Ro();
		if (result == 1) {
			String msg = "删除成功";
			_log.info("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult(ResultDic.SUCCESS);
			return ro;
		} else {
			String msg = "删除失败，找不到该记录";
			_log.error("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 查询公司/组织信息
	 * 
	 * @param keys
	 *            模糊查询的关键字
	 * @param pageNum
	 *            第几页
	 * @param pageSize
	 *            每页大小
	 * 
	 * @mbg.overrideByMethodName
	 */
	@GetMapping("/suc/org")
	PageInfo<SucOrgMo> list(@RequestParam(value = "keys", required = false) String keys,
			@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
		_log.info("list keys:" + keys + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SucOrgMo> result = svc.list(keys, pageNum, pageSize);
		_log.info("result: " + result);
		return result;
	}

	/*
	 * 获取所有组织
	 */
	@GetMapping("/suc/org/all")
	List<SucOrgMo> listAll() {
		_log.info("开始获取所有的组织");
		List<SucOrgMo> result = svc.listAll();
		_log.info("开始获取所有的组织结果为:{}", result);
		return result;
	}

	/**
	 * 根据组织名称查询组织信息
	 * 
	 * @param name
	 */
	@GetMapping("/suc/org/selectbyname")
	List<SucOrgMo> listByName(@RequestParam(value = "name", required = false) String name) {
		_log.info("查询组织信息的参数为：{}", name);
		return svc.listByName(name);
	}

	/**
	 * 设置启用或者禁用组织
	 * 
	 * @param id
	 * @param isEnabled
	 * @return
	 */
	@PutMapping("/suc/org/enable")
	SucOrgRo enable(@RequestParam("id") Long id, @RequestParam("isEnabled") Boolean isEnabled) {
		_log.info("禁用或者启用组织的参数为：id={}, isEnabled={}", id, isEnabled);
		return svc.enable(id, isEnabled);
	}

	/**
	 * 根据组织名称获取单个组织信息
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/suc/org/getone")
	SucOrgMo getOne(@RequestParam("name") String name) {
		_log.info("根据组织名称获取单个组织信息的参数为：{}", name);
		SucOrgMo mo = new SucOrgMo();
		mo.setName(name);
		return svc.getOne(mo);
	}

	/**
	 * 查询公司/组织信息
	 * 
	 * 
	 */
	@GetMapping("/suc/org/account")
	PageInfo<OrgAccountRo> listOrgAccount(final SucOrgMo mo, @RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		_log.info("listOrgAccount mo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return svc.listOrgAccount(mo, pageNum, pageSize);
	}

	/**
	 * 根据组织id集合查询已经存在和不存在的组织
	 * 
	 * @param id
	 *            组织ID
	 * @param pageNum
	 *            第几页
	 * @param pageSize
	 *            每页大小
	 */
	@GetMapping("/suc/org/listaddedandunaddedorgs")
	SucOrgInOrNotInRo listAddedAndUnaddedOrgs(@RequestParam("orgIds") String orgIds,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 7;
		_log.info("list id:" + orgIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return svc.listAddedAndUnaddedOrgs(orgIds, pageSize, null, pageNum, null, pageNum);
	}

	/**
	 * 查询指定组织的已添加的组织列表
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
	@GetMapping("/suc/org/listaddedorgs")
	PageInfo<SucOrgMo> listAddedOrgs(@RequestParam("orgIds") String orgIds,
			@RequestParam(value = "keys", required = false) String keys,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 7;
		_log.info("list id:" + orgIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SucOrgMo> added = svc.listAddedOrgs(orgIds, keys, pageNum, pageSize);
		_log.info("added: " + added.getList());
		return added;
	}

	/**
	 * 查询指定组织的未添加的组织列表
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
	@GetMapping("/suc/org/listunaddedorgs")
	PageInfo<SucOrgMo> listUnaddedOrgs(@RequestParam("orgIds") String orgIds,
			@RequestParam(value = "keys", required = false) String keys,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 7;
		_log.info("list orgIds:" + orgIds + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SucOrgMo> unadded = svc.listUnaddedOrgs(orgIds, keys, pageNum, pageSize);
		_log.info("unadded: " + unadded.getList());
		return unadded;
	}

}
