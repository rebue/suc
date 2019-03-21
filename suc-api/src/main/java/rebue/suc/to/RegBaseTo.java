package rebue.suc.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 注册与登录的基础传输对象
 */
@Getter
@Setter
@ToString
public abstract class RegBaseTo extends RegAndLoginBaseTo {
	/**
	 * 推广者ID
	 */
	private Long promoterId;

	/**
	 * 公司/组织id
	 *
	 * 数据库字段: SUC_USER.ORG_ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Long orgId;
	
	/**
	 * 是否为组织添加
	 */
	private Boolean isOrgAdd;
}
