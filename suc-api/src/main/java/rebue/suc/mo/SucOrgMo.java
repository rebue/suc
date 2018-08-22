package rebue.suc.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 公司/组织信息
 *
 * 数据库表: SUC_ORG
 *
 * 自动生成，如需修改，请删除本行 @mbg.generated
 */
@JsonInclude(Include.NON_NULL)
public class SucOrgMo implements Serializable {

    /**
     *    数据库字段: SUC_ORG.CREATE_TIMESTAMP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long createTimestamp;

    /**
     *    数据库字段: SUC_ORG.CREATE_TIMESTAMP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     *    数据库字段: SUC_ORG.CREATE_TIMESTAMP
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     *    数据库字段: SUC_ORG.IS_ENABLED
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Boolean isEnabled;

    /**
     *    数据库字段: SUC_ORG.IS_ENABLED
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    数据库字段: SUC_ORG.IS_ENABLED
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    创建时间
     *
     *    数据库字段: SUC_ORG.CREATE_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long createTime;

    /**
     *    创建时间
     *
     *    数据库字段: SUC_ORG.CREATE_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     *    创建时间
     *
     *    数据库字段: SUC_ORG.CREATE_TIME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_ORG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private Long id;

    /**
     *    公司/组织名称
     *
     *    数据库字段: SUC_ORG.NAME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String name;

    /**
     *    公司/组织备注
     *
     *    数据库字段: SUC_ORG.REMARK
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private String remark;

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_ORG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     *    公司/组织id
     *
     *    数据库字段: SUC_ORG.ID
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    公司/组织名称
     *
     *    数据库字段: SUC_ORG.NAME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     *    公司/组织名称
     *
     *    数据库字段: SUC_ORG.NAME
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    公司/组织备注
     *
     *    数据库字段: SUC_ORG.REMARK
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     *    公司/组织备注
     *
     *    数据库字段: SUC_ORG.REMARK
     *
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SucOrgMo other = (SucOrgMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    自动生成，如需修改，请删除本行 @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
