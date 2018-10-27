package rebue.suc.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.sbs.feign.FeignConfig;
import rebue.suc.mo.SucOrgMo;
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
}
