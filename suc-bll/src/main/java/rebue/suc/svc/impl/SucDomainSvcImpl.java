package rebue.suc.svc.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.robotech.svc.impl.MybatisBaseSvcImpl;
import rebue.suc.mapper.SucDomainMapper;
import rebue.suc.mo.SucDomainMo;
import rebue.suc.svc.SucDomainSvc;

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
public class SucDomainSvcImpl extends MybatisBaseSvcImpl<SucDomainMo, java.lang.String, SucDomainMapper> implements SucDomainSvc {

    /**
     * @mbg.generated
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(SucDomainMo mo) {
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId().trim().isEmpty()) {
            mo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        return super.add(mo);
    }
}
