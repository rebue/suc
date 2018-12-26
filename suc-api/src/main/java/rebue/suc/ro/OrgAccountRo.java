package rebue.suc.ro;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrgAccountRo {
	
	/**
	 * 供应商id
	 */
	private Long  supplierId;
	
	/**
	 * 供应商名字
	 */
	private String  supplierName;
	
	/**
	 * 账户余额
	 */
    private BigDecimal  balance;
    
	/**
	 * 已经结算的订单总额
	 */
	private BigDecimal AlreadySettle;
	
	/**
	 * 没有结算
	 */
	private BigDecimal notSettle;
    
}
