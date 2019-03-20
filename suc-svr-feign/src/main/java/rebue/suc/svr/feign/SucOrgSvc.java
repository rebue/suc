package rebue.suc.svr.feign;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

import rebue.sbs.feign.FeignConfig;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.SucOrgInOrNotInRo;
import rebue.suc.ro.SucOrgRo;

@FeignClient(name = "suc-svr", configuration = FeignConfig.class)
public interface SucOrgSvc {

	/**
     * 添加公司/组织信息
     */
    @PostMapping("/suc/org")
    SucOrgRo add(@RequestBody SucOrgMo mo);
    
    /**
     * 设置启用或者禁用组织
     * 
     * @param id
     * @param isEnabled
     * @return
     */
    @PutMapping("/suc/org/enable")
    SucOrgRo enable(@RequestParam("id") Long id, @RequestParam("isEnabled") Boolean isEnabled);
    
    /**
     * 修改公司/组织信息
     *
     * @mbg.generated
     */
    @PutMapping("/suc/org")
    SucOrgRo modify(@RequestBody SucOrgMo mo);
    
    /**
     * 根据名称判断组织是否存在
     * @param name
     * @return
     */
    @GetMapping("/suc/userorg/existbyname")
    boolean existByName(@RequestParam("name") String name);
    
    /**
     * 根据组织名称获取单个组织信息
     * @param name
     * @return
     */
    @GetMapping("/suc/org/getone")
    SucOrgMo getOne(@RequestParam("name") String name);
    
    /**
     * 根据ID获取单个组织信息
     * @param name
     * @return
     */
    @GetMapping("/suc/org/getbyid")
    SucOrgRo getById(@RequestParam("id") Long id);
    
    /**
     * 获取所有的组织
     * @return
     */
    @GetMapping("/suc/org/all")
    List<SucOrgMo> listAll();
    
    /**
     * 查询已经存在和没有存在
     * @param orgIds
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/suc/org/listaddedandunaddedorgs")
    SucOrgInOrNotInRo listAddedAndUnaddedOrgs(@RequestParam("orgIds") String orgIds, @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) ;
    
    /**
     * 查询已经存在的
     * @param orgIds
     * @param keys
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/suc/org/listaddedorgs")
    PageInfo<SucOrgMo> listAddedOrgs(@RequestParam("orgIds") String orgIds, @RequestParam(value = "keys", required = false) String keys,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize);
    
    /**
     * 
     * @param orgIds
     * @param keys
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/suc/org/listunaddedorgs")
    PageInfo<SucOrgMo> listUnaddedOrgs(@RequestParam("orgIds") String orgIds, @RequestParam(value = "keys", required = false) String keys,
            @RequestParam(value = "pageNum", required = false) Integer pageNum, @RequestParam(value = "pageSize", required = false) Integer pageSize);
}
